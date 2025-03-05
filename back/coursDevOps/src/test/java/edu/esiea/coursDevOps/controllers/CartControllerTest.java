package edu.esiea.coursDevOps.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.esiea.coursDevOps.exceptions.CartNotFoundException;
import edu.esiea.coursDevOps.exceptions.InvalidInputException;
import edu.esiea.coursDevOps.exceptions.ProductNotFoundException;
import edu.esiea.coursDevOps.models.Cart;
import edu.esiea.coursDevOps.models.Product;
import edu.esiea.coursDevOps.models.User;
import edu.esiea.coursDevOps.services.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CartController.class)
@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private CartService service;

    private final ObjectMapper mapper = new ObjectMapper();

    // Test data
    private static final User TEST_USER = new User(1, "login", "password");
    private static final Product TEST_PRODUCT = new Product(1, "Product 1", "Description 1", "image.jpg", 10.0f, 0.2f);
    private static final Cart TEST_CART = new Cart(1, TEST_USER, 1, 10.0f);

    @Test
    void testGetAllCarts() throws Exception {
        // Given
        when(service.getAll()).thenReturn(List.of(TEST_CART));

        // When & Then
        mockMvc.perform(get("/carts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        // Verify
        verify(service, times(1)).getAll();
    }

    @Test
    void testCreateCart() throws Exception {
        // Given
        Cart savedCart = new Cart(123, TEST_USER, 1, 10.0f); // Simulate saved cart with ID

        when(service.createCart(any(User.class), anyInt(), anyFloat())).thenReturn(savedCart); // Match any input

        // When & Then
        mockMvc.perform(post("/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TEST_CART)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(savedCart.getId()));

        // Verify
        verify(service, times(1)).createCart(any(User.class), anyInt(), anyFloat());
    }
    @Test
    void testUpdateCart() throws Exception {
        // Given
        Cart updatedCart = new Cart(1, TEST_USER, 2, 20.0f);
        when(service.updateCart(eq(1), any(User.class), anyInt(), anyFloat())).thenReturn(updatedCart);

        // When & Then
        mockMvc.perform(put("/carts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updatedCart)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedCart.getId()))
                .andExpect(jsonPath("$.quantity").value(updatedCart.getQuantity()))
                .andExpect(jsonPath("$.totalPrice").value(updatedCart.getTotalPrice()));

        // Verify
        verify(service, times(1)).updateCart(eq(1), any(User.class), anyInt(), anyFloat());
    }

    @Test
    void testUpdateCart_WhenCartNotFound_ShouldThrowCartNotFoundException() throws Exception {
        // Given
        Cart cart = new Cart(0, new User(1, "login", "password"), 2, 20.0f);

        // Configure the mock to throw the CartNotFoundException
        when(service.updateCart(eq(1), any(User.class), anyInt(), anyFloat()))
                .thenThrow(new CartNotFoundException("Cart with ID 1 not found."));

        // Convert the object to JSON
        String json = mapper.writeValueAsString(cart);

        // When & Then
        mockMvc.perform(put("/carts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound()) // Expect 404
                .andExpect(jsonPath("$.message").value("Cart with ID 1 not found."));

        // Verify
        verify(service, times(1)).updateCart(eq(1), any(User.class), anyInt(), anyFloat());
    }


    @Test
    void testDeleteCart() throws Exception {
        // Given
        doNothing().when(service).deleteCart(1);

        // When & Then
        mockMvc.perform(delete("/carts/1"))
                .andExpect(status().isNoContent());

        // Verify
        verify(service, times(1)).deleteCart(1);
    }

    @Test
    void testDeleteCart_WhenCartNotFound_ShouldThrowCartNotFoundException() throws Exception {
        // Given
        doThrow(new CartNotFoundException("Cart with ID 1 not found.")).when(service).deleteCart(1);

        // When & Then
        mockMvc.perform(delete("/carts/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Cart with ID 1 not found."));

        // Verify
        verify(service, times(1)).deleteCart(1);
    }

    @Test
    void testAddProductToCart() throws Exception {
        // Given
        doNothing().when(service).addProductToCart(1, TEST_PRODUCT);
        when(service.getCartById(1)).thenReturn(TEST_CART);

        // When & Then
        mockMvc.perform(post("/carts/1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TEST_PRODUCT)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TEST_CART.getId()));

        // Verify
        verify(service, times(1)).addProductToCart(1, TEST_PRODUCT);
        verify(service, times(1)).getCartById(1);
    }

    @Test
    void testAddProductToCart_WhenCartNotFound_ShouldThrowCartNotFoundException() throws Exception {
        // Given
        doThrow(new CartNotFoundException("Cart with ID 1 not found.")).when(service).addProductToCart(1, TEST_PRODUCT);

        // When & Then
        mockMvc.perform(post("/carts/1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TEST_PRODUCT)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Cart with ID 1 not found."));

        // Verify
        verify(service, times(1)).addProductToCart(1, TEST_PRODUCT);
    }

    @Test
    void testRemoveProductFromCart() throws Exception {
        // Given
        Product product = new Product(1, "Product 1", "Description 1", "image.jpg", 10.0f, 0.2f);
        doNothing().when(service).removeProductFromCart(1, product);
        when(service.getCartById(1)).thenReturn(TEST_CART);

        // When & Then
        mockMvc.perform(delete("/carts/1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TEST_CART.getId()));

        // Verify
        verify(service, times(1)).removeProductFromCart(1, product);
        verify(service, times(1)).getCartById(1);
    }

    @Test
    void testRemoveProductFromCart_WhenCartNotFound_ShouldThrowCartNotFoundException() throws Exception {
        // Given
        Product product = new Product(1, "Product 1", "Description 1", "image.jpg", 10.0f, 0.2f);
        doThrow(new CartNotFoundException("Cart with ID 1 not found.")).when(service).removeProductFromCart(1, product);

        // Convert the object to JSON
        String json = mapper.writeValueAsString(product);

        // When & Then
        mockMvc.perform(delete("/carts/1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound()) // Expect 404
                .andExpect(jsonPath("$.message").value("Cart with ID 1 not found."));

        // Verify
        verify(service, times(1)).removeProductFromCart(1, product);
    }
}
