package com.galli.gifts.controller;

import com.galli.gifts.entity.Product;
import com.galli.gifts.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.valueOf(100.0));
        product.setStockQuantity(10);
        product.setSoldQuantity(5);
        product.setCategory("Test Category");
    }

    @Test
    void createProduct_ShouldReturnCreatedProduct() throws Exception {
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content("{\"name\":\"Test Product\",\"description\":\"Test Description\",\"price\":100.0,\"stockQuantity\":10,\"soldQuantity\":5,\"category\":\"Test Category\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.price").value(100.0))
                .andExpect(jsonPath("$.stockQuantity").value(10))
                .andExpect(jsonPath("$.soldQuantity").value(5))
                .andExpect(jsonPath("$.category").value("Test Category"));

        verify(productService, times(1)).createProduct(any(Product.class));
    }

    @Test
    void getProductById_ShouldReturnProduct_WhenExists() throws Exception {
        when(productService.getProductById(anyLong())).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.price").value(100.0))
                .andExpect(jsonPath("$.stockQuantity").value(10))
                .andExpect(jsonPath("$.soldQuantity").value(5))
                .andExpect(jsonPath("$.category").value("Test Category"));

        verify(productService, times(1)).getProductById(anyLong());
    }

    @Test
    void getProductById_ShouldReturnNotFound_WhenNotExists() throws Exception {
        when(productService.getProductById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).getProductById(anyLong());
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() throws Exception {
        List<Product> products = Arrays.asList(product, product);
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product"))
                .andExpect(jsonPath("$[1].name").value("Test Product"));

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void updateProduct_ShouldReturnUpdatedProduct_WhenExists() throws Exception {
        when(productService.updateProduct(anyLong(), any(Product.class))).thenReturn(Optional.of(product));

        mockMvc.perform(put("/products/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Updated Product\",\"description\":\"Updated Description\",\"price\":150.0,\"stockQuantity\":20,\"soldQuantity\":20,\"category\":\"Updated Category\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.price").value(100.0))
                .andExpect(jsonPath("$.stockQuantity").value(10))
                .andExpect(jsonPath("$.soldQuantity").value(5))
                .andExpect(jsonPath("$.category").value("Test Category"));

        verify(productService, times(1)).updateProduct(anyLong(), any(Product.class));
    }

    @Test
    void updateProduct_ShouldReturnNotFound_WhenProductNotFound() throws Exception {
        when(productService.updateProduct(anyLong(), any(Product.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/products/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Updated Product\",\"description\":\"Updated Description\",\"price\":150.0,\"stockQuantity\":10,\"soldQuantity\":5,\"category\":\"Updated Category\"}"))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).updateProduct(anyLong(), any(Product.class));
    }

    @Test
    void deleteProduct_ShouldReturnNoContent_WhenProductExists() throws Exception {
        when(productService.deleteProduct(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).deleteProduct(anyLong());
    }

    @Test
    void deleteProduct_ShouldReturnNotFound_WhenProductNotFound() throws Exception {
        when(productService.deleteProduct(anyLong())).thenReturn(false);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).deleteProduct(anyLong());
    }
}
