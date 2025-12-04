package com.example.demo.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class ProductRequestDTO {

    @NotBlank(message = "name must be provided")
    @Size(min = 2, message = "name must contain at least 2 characters")
    private String name;
    @Positive(message = "price must be positive")
    private Double price;

    //användren behöver inte veta drtta
    private String internalRating;

    private Long categoryId;
    private Set<Long> supplierIds;
    private String detailsDescription;

    private String manufacturer;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String name, Double price) {
        this.name = name;
        this.price = price;
        this.internalRating = "";
    }

    public ProductRequestDTO(String name, Double price, String rating) {
        this.name = name;
        this.price = price;
        this.internalRating = rating;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Set<Long> getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(Set<Long> supplierIds) {
        this.supplierIds = supplierIds;
    }

    public String getDetailsDescription() {
        return detailsDescription;
    }

    public void setDetailsDescription(String detailsDescription) {
        this.detailsDescription = detailsDescription;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInternalRating() {
        return internalRating;
    }

    public void setInternalRating(String internalRating) {
        this.internalRating = internalRating;
    }

    @Override
    public String toString() {
        return "ProductRequestDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", internalRating='" + internalRating + '\'' +
                '}';
    }
}
