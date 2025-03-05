package edu.esiea.coursDevOps.controllers;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.esiea.coursDevOps.exceptions.CartNotFoundException;
import edu.esiea.coursDevOps.exceptions.InvalidInputException;
import edu.esiea.coursDevOps.exceptions.ProductNotFoundException;
import edu.esiea.coursDevOps.models.Cart;
import edu.esiea.coursDevOps.models.Product;
import edu.esiea.coursDevOps.services.CartService;



@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    // Retrieve all carts
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(service.getAll());
    }

    // Create a new cart
    @PostMapping
    public ResponseEntity<Cart> createCart(@Valid @RequestBody Cart cart) throws InvalidInputException {
        Cart createdCart = service.createCart(cart.getUser(), cart.getQuantity(), cart.getTotalPrice());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
    }

    // Update an existing cart
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable int id, @Valid @RequestBody Cart cart)
            throws CartNotFoundException, InvalidInputException {
        Cart updatedCart = service.updateCart(id, cart.getUser(), cart.getQuantity(), cart.getTotalPrice());
        return ResponseEntity.ok(updatedCart);
    }

    // Delete a cart by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable int id) throws CartNotFoundException {
        service.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

    // Add a product to a cart
    @PostMapping("/{cartId}/products")
    public ResponseEntity<Cart> addProductToCart(@PathVariable int cartId, @Valid @RequestBody Product product)
            throws CartNotFoundException, ProductNotFoundException {
        service.addProductToCart(cartId, product);
        return ResponseEntity.ok(service.getCartById(cartId)); // Return the updated cart
    }

    // Remove a product from a cart
    @DeleteMapping("/{cartId}/products")
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable int cartId, @Valid @RequestBody Product product)
            throws CartNotFoundException, ProductNotFoundException {
        service.removeProductFromCart(cartId, product);
        return ResponseEntity.ok(service.getCartById(cartId)); // Return the updated cart
    }

    // Exception handling
    @ExceptionHandler({CartNotFoundException.class, ProductNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
