package com.galli.gifts.repository;

import com.galli.gifts.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT DISTINCT category FROM product ORDER BY category", nativeQuery = true)
    List<String> findAllDistinctCategories();

    @Query(value = "SELECT * FROM product WHERE category = :category", nativeQuery = true)
    List<Product> findAllProductsByCategory(@Param("category") String category);
}
