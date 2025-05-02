package com.galli.gifts.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void testProductConstructorAndGetters() {
        Long expectedId = 1L;
        String expectedName = "Test Product";
        String expectedDescription = "Test Description";
        String expectedCategory = "Test Category";
        Integer expectedStockQuantity = 10;
        Integer expectedSoldQuantity = 5;
        Integer expectedQuantityInCart = 1;
        BigDecimal expectedPrice = BigDecimal.valueOf(100.0);
        BigDecimal expectedDiscountPercentage = BigDecimal.valueOf(0.1);
        BigDecimal expectedTotalGrossSales = BigDecimal.valueOf(500.0);

        Product product = new Product(expectedId, expectedName, expectedDescription, expectedCategory, expectedStockQuantity, expectedSoldQuantity, expectedQuantityInCart, expectedPrice, expectedDiscountPercentage, expectedTotalGrossSales);

        assertEquals(expectedId, product.getId());
        assertEquals(expectedName, product.getName());
        assertEquals(expectedDescription, product.getDescription());
        assertEquals(expectedCategory, product.getCategory());
        assertEquals(expectedStockQuantity, product.getStockQuantity());
        assertEquals(expectedSoldQuantity, product.getSoldQuantity());
        assertEquals(expectedQuantityInCart, product.getQuantityInCart());
        assertEquals(expectedPrice, product.getPrice());
        assertEquals(expectedDiscountPercentage, product.getDiscountPercentage());
        assertEquals(expectedTotalGrossSales, product.getTotalGrossSales());
    }

    @Test
    void testSettersAndGetters() {
        Product product = new Product();

        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setCategory("Test Category");
        product.setStockQuantity(10);
        product.setSoldQuantity(5);
        product.setQuantityInCart(1);
        product.setPrice(BigDecimal.valueOf(100.0));
        product.setDiscountPercentage(BigDecimal.valueOf(0.1));
        product.setTotalGrossSales(BigDecimal.valueOf(500.0));

        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals("Test Description", product.getDescription());
        assertEquals("Test Category", product.getCategory());
        assertEquals(10, product.getStockQuantity());
        assertEquals(5, product.getSoldQuantity());
        assertEquals(1, product.getQuantityInCart());
        assertEquals(BigDecimal.valueOf(100.0), product.getPrice());
        assertEquals(BigDecimal.valueOf(0.1), product.getDiscountPercentage());
        assertEquals(BigDecimal.valueOf(500.0), product.getTotalGrossSales());
    }
}
