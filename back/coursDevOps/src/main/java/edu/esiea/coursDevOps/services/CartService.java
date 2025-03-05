package edu.esiea.coursDevOps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.coursDevOps.exceptions.CartNotFoundException;
import edu.esiea.coursDevOps.exceptions.InvalidInputException;
import edu.esiea.coursDevOps.exceptions.ProductNotFoundException;
import edu.esiea.coursDevOps.models.Cart;
import edu.esiea.coursDevOps.models.Product;
import edu.esiea.coursDevOps.models.User;
import edu.esiea.coursDevOps.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private final CartRepository repo;

    public CartService(CartRepository repo) {
        this.repo = repo;
    }

    // Retrieve all carts
    public List<Cart> getAll() {
        return repo.findAll();
    }
    
    // Retrieve a cart by ID
    public Cart getCartById(int id) throws CartNotFoundException {
        return repo.findById(id)
                .orElseThrow(() -> new CartNotFoundException("Cart with ID " + id + " not found."));
    }

    // Create a new cart
    public Cart createCart(User user, int quantity, float totalPrice) throws InvalidInputException {
        if (quantity < 0 || totalPrice < 0) {
            throw new InvalidInputException("Quantity and total price must be non-negative.");
        }
        Cart cart = new Cart(0, user, quantity, totalPrice);
        return repo.save(cart);
    }

 // Update an existing cart
    public Cart updateCart(int id, User user, int quantity, float totalPrice) throws CartNotFoundException, InvalidInputException {
        // Validate input parameters
        if (quantity < 0) {
            throw new InvalidInputException("Quantity must be non-negative.");
        }
        if (totalPrice < 0) {
            throw new InvalidInputException("Total price must be non-negative.");
        }
        if (user == null) {
            throw new InvalidInputException("User cannot be null.");
        }

        // Check if the cart exists
        Optional<Cart> existingCart = repo.findById(id);
        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();

            // Update cart fields
            cart.setUser(user);
            cart.setQuantity(quantity);
            cart.setTotalPrice(totalPrice);

            // Save and return the updated cart
            return repo.save(cart);
        } else {
            // Throw exception if cart is not found
            throw new CartNotFoundException("Cart with ID " + id + " not found.");
        }
    }
    // Delete a cart by ID
    public void deleteCart(int id) throws CartNotFoundException {
        Optional<Cart> existingCart = repo.findById(id);
        if (existingCart.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new CartNotFoundException("Cart with ID " + id + " not found.");
        }
    }

    // Add a product to a cart
    public void addProductToCart(int cartId, Product product) throws CartNotFoundException, ProductNotFoundException {
        if (product == null) {
            throw new ProductNotFoundException("Product cannot be null.");
        }
        Optional<Cart> existingCart = repo.findById(cartId);
        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.addProduct(product);
            repo.save(cart);
        } else {
            throw new CartNotFoundException("Cart with ID " + cartId + " not found.");
        }
    }

    // Remove a product from a cart
    public void removeProductFromCart(int cartId, Product product) throws CartNotFoundException, ProductNotFoundException {
        if (product == null) {
            throw new ProductNotFoundException("Product cannot be null.");
        }
        Optional<Cart> existingCart = repo.findById(cartId);
        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.removeProduct(product);
            repo.save(cart);
        } else {
            throw new CartNotFoundException("Cart with ID " + cartId + " not found.");
        }
    }
}