package com.galli.gifts.service;

import com.galli.gifts.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product createProduct(Product product);

    Optional<Product> getProductById(Long id);

    List<Product> getAllProducts();

    List<String> getAllCategories();

    List<Product> getAllProductsByCategory(String category);

    Optional<Product> updateProduct(Long id, Product product);

    void processPurchase(List<Product> purchasedProducts);

    boolean deleteProduct(Long id);
}
