package com.galli.gifts.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void testProductConstructorAndGetters() {
        Long expectedId = 1L;
        String expectedName = "Test Product";
        String expectedDescription = "Test Description";
        Double expectedPrice = 100.0;
        Integer expectedQuantity = 10;
        String expectedCategory = "Test Category";

        Product product = new Product(expectedId, expectedName, expectedDescription, expectedPrice, expectedQuantity, expectedCategory);

        assertEquals(expectedId, product.getId());
        assertEquals(expectedName, product.getName());
        assertEquals(expectedDescription, product.getDescription());
        assertEquals(expectedPrice, product.getPrice());
        assertEquals(expectedQuantity, product.getQuantity());
        assertEquals(expectedCategory, product.getCategory());
    }

    @Test
    void testSettersAndGetters() {
        Product product = new Product();

        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.0);
        product.setQuantity(10);
        product.setCategory("Test Category");

        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals("Test Description", product.getDescription());
        assertEquals(100.0, product.getPrice());
        assertEquals(10, product.getQuantity());
        assertEquals("Test Category", product.getCategory());
    }
}
