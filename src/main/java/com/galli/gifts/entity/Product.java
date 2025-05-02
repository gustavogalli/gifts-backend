package com.galli.gifts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String category;
    private Integer stockQuantity;
    private Integer soldQuantity;
    private Integer quantityInCart;
    private BigDecimal price;
    private BigDecimal discountPercentage;
    private BigDecimal totalGrossSales;

    public Product() {
    }

    public Product(Long id, String name, String description, String category, Integer stockQuantity, Integer soldQuantity, Integer quantityInCart, BigDecimal price, BigDecimal discountPercentage, BigDecimal totalGrossSales) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.soldQuantity = soldQuantity;
        this.quantityInCart = quantityInCart;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.totalGrossSales = totalGrossSales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Integer getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(Integer quantityInCart) {
        this.quantityInCart = quantityInCart;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getTotalGrossSales() {
        return totalGrossSales;
    }

    public void setTotalGrossSales(BigDecimal totalGrossSales) {
        this.totalGrossSales = totalGrossSales;
    }
}
