package com.galli.gifts.service.impl;

import com.galli.gifts.entity.Product;
import com.galli.gifts.repository.ProductRepository;
import com.galli.gifts.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public List<String> getAllCategories(){
        return productRepository.findAllDistinctCategories();
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return productRepository.findAllProductsByCategory(category);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();
            productToUpdate.setName(updatedProduct.getName());
            productToUpdate.setDescription(updatedProduct.getDescription());
            productToUpdate.setCategory(updatedProduct.getCategory());
            productToUpdate.setStockQuantity(updatedProduct.getStockQuantity());
            productToUpdate.setSoldQuantity(updatedProduct.getSoldQuantity());
            productToUpdate.setPrice(updatedProduct.getPrice());
            productToUpdate.setDiscountPercentage(updatedProduct.getDiscountPercentage());

            return Optional.of(productRepository.save(productToUpdate));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void processPurchase(List<Product> purchasedProducts) {
        for (Product purchasedProduct : purchasedProducts) {
            productRepository.findById(purchasedProduct.getId()).ifPresent(product -> {
                int quantityInCart = purchasedProduct.getQuantityInCart();
                int newStock = product.getStockQuantity() - quantityInCart;
                int newSold = product.getSoldQuantity() + quantityInCart;

                product.setStockQuantity(newStock);
                product.setSoldQuantity(newSold);
                product.setPrice(product.getPrice().multiply(BigDecimal.ONE.subtract(product.getDiscountPercentage())));
                product.setTotalGrossSales(product.getTotalGrossSales().add(product.getPrice().multiply(BigDecimal.valueOf(quantityInCart))));

                productRepository.save(product);
            });
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
