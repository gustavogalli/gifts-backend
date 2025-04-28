package com.galli.gifts.service.impl;

import com.galli.gifts.entity.Product;
import com.galli.gifts.repository.ProductRepository;
import com.galli.gifts.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();
            productToUpdate.setName(updatedProduct.getName());
            productToUpdate.setDescription(updatedProduct.getDescription());
            productToUpdate.setPrice(updatedProduct.getPrice());
            productToUpdate.setQuantity(updatedProduct.getQuantity());
            productToUpdate.setCategory(updatedProduct.getCategory());

            return Optional.of(productRepository.save(productToUpdate));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            productRepository.delete(existingProduct.get());
            return true;
        }

        return false;
    }
}
