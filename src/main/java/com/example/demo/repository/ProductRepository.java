package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {


    //SQL @Query(value = "SELECT * FROM products WHERE name LIKE :keyword",nativeQuery = true)
    //JPQL
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    public List<Product> searchByName(@Param("keyword") String name);

    public List<Product> findByNameContaining(String name);

//derived Query JPA
    public List<Product> findByCategoryName(String name);

    public List<Product> findByCategoryNameAndPriceLessThan(String category, double price);

    public List<Product> findByPriceBetween(Double min, Double max);

    public List<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.price > :min")
    public List<Product> searchExpensive(@Param("min") double price);

    @Query(value = """
SELECT p.* FROM products p JOIN 
categories c ON p.category_id = c.id
WHERE c.name = :name
""", nativeQuery = true)
    public List<Product> searchByCategoryName(@Param("name") String categoryName);
}

