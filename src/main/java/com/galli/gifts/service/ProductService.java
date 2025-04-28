package com.galli.gifts.service;

import com.galli.gifts.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product createProduct(Product product);

    Optional<Product> getProductById(Long id);

    List<Product> getAllProducts();

    Optional<Product> updateProduct(Long id, Product product);

    boolean deleteProduct(Long id);
}
