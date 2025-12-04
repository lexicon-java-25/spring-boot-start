package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private SupplierRepository supplierRepository;
    private ProductDetailsRepository productDetailsRepository;

    public TestController(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          SupplierRepository supplierRepository,
                          ProductDetailsRepository productDetailsRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.productDetailsRepository = productDetailsRepository;
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    @GetMapping("/details")
    public List<ProductDetails> getProductDetails(){
        return productDetailsRepository.findAll();
    }
    @GetMapping("/suppliers")
    public List<Supplier> getSuppliers(){
        return supplierRepository.findAll();
    }
    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }


}
