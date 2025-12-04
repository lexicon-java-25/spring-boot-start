package com.example.demo.model.dto;

import com.example.demo.model.ProductDetails;

import java.util.Set;

public class ProductResponseDTO {

    private int id;

    private String name;
    private double price;

    private String category;

    private Set<String> suppliers;

    private ProductDetails details;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(int id, String name, double price, String category, Set<String> suppliers, ProductDetails details) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.suppliers = suppliers;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<String> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<String> suppliers) {
        this.suppliers = suppliers;
    }

    public ProductDetails getDetails() {
        return details;
    }

    public void setDetails(ProductDetails details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ProductResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
