package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.Supplier;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ProductIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SupplierRepository supplierRepository;


    int savedProductId;


    @BeforeEach
    void setup(){
        productRepository.deleteAll();
        categoryRepository.deleteAll();
        supplierRepository.deleteAll();

        Category cat = new Category();
        cat.setName("fruit");
        categoryRepository.save(cat);

        Supplier supplier = new Supplier();
        supplier.setName("local supplier");
        supplierRepository.save(supplier);

        Product p = new Product();
        p.setName("Banana");
        p.setPrice(10.0);
        p.setCategory(cat);
        p.setSuppliers(Set.of(supplier));

        savedProductId = productRepository.save(p).getId();
    }

    //public ResponseEntity<ProductResponseDTO> getByid(@PathVariable int id)

    @Test
    @DisplayName("getById should return status 200 and productDTO")
    void getByIdReturnProduct() throws Exception{
        mockMvc.perform(get("/products/" + savedProductId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Banana"))
                .andExpect(jsonPath("$.category").value("fruit"));


    }




}