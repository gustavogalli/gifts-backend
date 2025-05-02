package com.galli.gifts.service.impl;

import com.galli.gifts.entity.Product;
import com.galli.gifts.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setDescription("Product 1 Description");
        product.setCategory("Category 1");
        product.setStockQuantity(100);
        product.setSoldQuantity(50);
        product.setPrice(BigDecimal.valueOf(100));
        product.setDiscountPercentage(BigDecimal.valueOf(0.1));
        product.setTotalGrossSales(BigDecimal.valueOf(5000));
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals(product.getName(), createdProduct.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> retrievedProduct = productService.getProductById(1L);

        assertTrue(retrievedProduct.isPresent());
        assertEquals(product.getId(), retrievedProduct.get().getId());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(BigDecimal.valueOf(150));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        Optional<Product> result = productService.updateProduct(1L, updatedProduct);

        assertTrue(result.isPresent());
        assertEquals("Updated Product", result.get().getName());
        assertEquals(BigDecimal.valueOf(150), result.get().getPrice());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDeleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        boolean result = productService.deleteProduct(1L);

        assertTrue(result);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void testDeleteProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = productService.deleteProduct(1L);

        assertFalse(result);
        verify(productRepository, times(0)).delete(any());
    }

    @Test
    public void testProcessPurchase() {
        Product purchasedProduct = new Product();
        purchasedProduct.setId(1L);
        purchasedProduct.setQuantityInCart(10);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        productService.processPurchase(List.of(purchasedProduct));

        assertEquals(90, product.getStockQuantity());
        assertEquals(60, product.getSoldQuantity());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testProcessPurchaseProductNotFound() {
        Product purchasedProduct = new Product();
        purchasedProduct.setId(1L);
        purchasedProduct.setQuantityInCart(10);

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        productService.processPurchase(List.of(purchasedProduct));

        verify(productRepository, times(0)).save(any());
    }


}
