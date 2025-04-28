package com.galli.gifts.service.impl;

import com.galli.gifts.entity.Product;
import com.galli.gifts.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.0);
        product.setQuantity(10);
        product.setCategory("Test Category");
    }

    @Test
    void createProduct_ShouldReturnProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getName());
        assertEquals("Test Description", createdProduct.getDescription());
        assertEquals(100.0, createdProduct.getPrice());
        assertEquals(10, createdProduct.getQuantity());
        assertEquals("Test Category", createdProduct.getCategory());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void getProductById_ShouldReturnProduct_WhenExists() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals("Test Product", foundProduct.get().getName());
        verify(productRepository, times(1)).findById(anyLong());
    }

    @Test
    void getProductById_ShouldReturnEmpty_WhenNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Product> foundProduct = productService.getProductById(1L);

        assertFalse(foundProduct.isPresent());
        verify(productRepository, times(1)).findById(anyLong());
    }

    @Test
    void updateProduct_ShouldReturnUpdatedProduct_WhenExists() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        product.setName("Updated Product");
        Product updatedProduct = productService.updateProduct(1L, product).orElse(null);

        assertNotNull(updatedProduct);
        assertEquals("Updated Product", updatedProduct.getName());
        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void updateProduct_ShouldReturnEmpty_WhenProductNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Product> updatedProduct = productService.updateProduct(1L, product);

        assertTrue(updatedProduct.isEmpty());
        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void deleteProduct_ShouldReturnTrue_WhenProductExists() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        boolean isDeleted = productService.deleteProduct(1L);

        assertTrue(isDeleted);
        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, times(1)).delete(any(Product.class));
    }

    @Test
    void deleteProduct_ShouldReturnFalse_WhenProductNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        boolean isDeleted = productService.deleteProduct(1L);

        assertFalse(isDeleted);
        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, never()).delete(any(Product.class));
    }
}
