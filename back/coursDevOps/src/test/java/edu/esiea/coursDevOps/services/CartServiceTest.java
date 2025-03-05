package edu.esiea.coursDevOps.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.esiea.coursDevOps.exceptions.CartNotFoundException;
import edu.esiea.coursDevOps.exceptions.InvalidInputException;
import edu.esiea.coursDevOps.exceptions.ProductNotFoundException;
import edu.esiea.coursDevOps.models.Cart;
import edu.esiea.coursDevOps.models.Product;
import edu.esiea.coursDevOps.models.User;
import edu.esiea.coursDevOps.repository.CartRepository;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository repo;

    @InjectMocks
    private CartService service;

    // Test data
    private static final User TEST_USER = new User(1, "login", "password");
    private static final Product TEST_PRODUCT = new Product(1, "Product 1", "Description 1", "image.jpg", 10.0f, 0.2f);
    private static final Cart TEST_CART = new Cart(1, TEST_USER, 1, 10.0f);

    @Test
    void testGetAll_ShouldReturnListOfCarts() {
        // Given
        when(repo.findAll()).thenReturn(List.of(TEST_CART));

        // When
        List<Cart> carts = service.getAll();

        // Then
        assertNotNull(carts);
        assertEquals(1, carts.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testGetCartById_ShouldReturnCart() throws CartNotFoundException {
        // Given
        when(repo.findById(1)).thenReturn(Optional.of(TEST_CART));

        // When
        Cart cart = service.getCartById(1);

        // Then
        assertNotNull(cart);
        assertEquals(TEST_CART.getId(), cart.getId());
        verify(repo, times(1)).findById(1);
    }

    @Test
    void testGetCartById_WhenCartNotFound_ShouldThrowCartNotFoundException() {
        // Given
        when(repo.findById(1)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(CartNotFoundException.class, () -> service.getCartById(1));
        verify(repo, times(1)).findById(1);
    }

    @Test
    void testCreateCart_ShouldReturnCreatedCart() throws InvalidInputException {
        // Given
        when(repo.save(any(Cart.class))).thenReturn(TEST_CART);

        // When
        Cart cart = service.createCart(TEST_USER, 1, 10.0f);

        // Then
        assertNotNull(cart);
        assertEquals(TEST_CART.getId(), cart.getId());
        verify(repo, times(1)).save(any(Cart.class));
    }

    @Test
    void testCreateCart_WhenInvalidInput_ShouldThrowInvalidInputException() {
        // When & Then
        assertThrows(InvalidInputException.class, () -> service.createCart(TEST_USER, -1, 10.0f));
        verify(repo, never()).save(any(Cart.class));
    }

    @Test
    void testUpdateCart_ShouldReturnUpdatedCart() throws CartNotFoundException, InvalidInputException {
        // Given
        when(repo.findById(1)).thenReturn(Optional.of(TEST_CART));
        when(repo.save(any(Cart.class))).thenReturn(TEST_CART);

        // When
        Cart cart = service.updateCart(1, TEST_USER, 2, 20.0f);

        // Then
        assertNotNull(cart);
        assertEquals(TEST_CART.getId(), cart.getId());
        verify(repo, times(1)).findById(1);
        verify(repo, times(1)).save(any(Cart.class));
    }

    @Test
    void testUpdateCart_WhenCartNotFound_ShouldThrowCartNotFoundException() {
        // Given
        when(repo.findById(1)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(CartNotFoundException.class, () -> service.updateCart(1, TEST_USER, 2, 20.0f));
        verify(repo, times(1)).findById(1);
    }

    @Test
    void testUpdateCart_WhenInvalidInput_ShouldThrowInvalidInputException() {
        // Given
        int invalidQuantity = -1;

        // When & Then
        assertThrows(InvalidInputException.class, () -> service.updateCart(1, TEST_USER, invalidQuantity, 20.0f));

        // Verify that repo.findById(1) was NOT called
        verify(repo, never()).findById(1);
    }
    
    @Test
    void testUpdateCart_WhenValidInput_ShouldCallRepository() throws CartNotFoundException, InvalidInputException {
        // Given
        when(repo.findById(1)).thenReturn(Optional.of(TEST_CART));

        // When
        service.updateCart(1, TEST_USER, 5, 20.0f);

        // Then
        verify(repo, times(1)).findById(1);
    }

    @Test
    void testDeleteCart_ShouldDeleteCart() throws CartNotFoundException {
        // Given
        when(repo.findById(1)).thenReturn(Optional.of(TEST_CART));

        // When
        service.deleteCart(1);

        // Then
        verify(repo, times(1)).findById(1);
        verify(repo, times(1)).deleteById(1);
    }

    @Test
    void testDeleteCart_WhenCartNotFound_ShouldThrowCartNotFoundException() {
        // Given
        when(repo.findById(1)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(CartNotFoundException.class, () -> service.deleteCart(1));
        verify(repo, times(1)).findById(1);
    }

    @Test
    void testAddProductToCart_ShouldAddProduct() throws CartNotFoundException, ProductNotFoundException {
        // Given
        when(repo.findById(1)).thenReturn(Optional.of(TEST_CART));
        when(repo.save(any(Cart.class))).thenReturn(TEST_CART);

        // When
        service.addProductToCart(1, TEST_PRODUCT);

        // Then
        assertTrue(TEST_CART.getProducts().contains(TEST_PRODUCT));
        verify(repo, times(1)).findById(1);
        verify(repo, times(1)).save(any(Cart.class));
    }

    @Test
    void testAddProductToCart_WhenCartNotFound_ShouldThrowCartNotFoundException() {
        // Given
        when(repo.findById(1)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(CartNotFoundException.class, () -> service.addProductToCart(1, TEST_PRODUCT));
        verify(repo, times(1)).findById(1);
    }

    @Test
    void testAddProductToCart_WhenProductIsNull_ShouldThrowProductNotFoundException() {
        // When & Then
        assertThrows(ProductNotFoundException.class, () -> service.addProductToCart(1, null));
        verify(repo, never()).findById(1);
    }

    @Test
    void testRemoveProductFromCart_ShouldRemoveProduct() throws CartNotFoundException, ProductNotFoundException {
        // Given
        TEST_CART.addProduct(TEST_PRODUCT);
        when(repo.findById(1)).thenReturn(Optional.of(TEST_CART));
        when(repo.save(any(Cart.class))).thenReturn(TEST_CART);

        // When
        service.removeProductFromCart(1, TEST_PRODUCT);

        // Then
        assertFalse(TEST_CART.getProducts().contains(TEST_PRODUCT));
        verify(repo, times(1)).findById(1);
        verify(repo, times(1)).save(any(Cart.class));
    }

    @Test
    void testRemoveProductFromCart_WhenCartNotFound_ShouldThrowCartNotFoundException() {
        // Given
        when(repo.findById(1)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(CartNotFoundException.class, () -> service.removeProductFromCart(1, TEST_PRODUCT));
        verify(repo, times(1)).findById(1);
    }

    @Test
    void testRemoveProductFromCart_WhenProductIsNull_ShouldThrowProductNotFoundException() {
        // When & Then
        assertThrows(ProductNotFoundException.class, () -> service.removeProductFromCart(1, null));
        verify(repo, never()).findById(1);
    }
}