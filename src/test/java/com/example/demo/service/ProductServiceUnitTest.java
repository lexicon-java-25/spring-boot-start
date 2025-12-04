package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductResponseDTO;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceUnitTest {

    @Mock
    ProductRepository repository;

    @Spy
    @InjectMocks
    ProductService service;


    Product entity;

    ProductResponseDTO dto;
    @BeforeEach
    void setUp() {
        entity = new Product(1,"Laptop",1000.0,"bra");

        dto = new ProductResponseDTO();
        dto.setName("Laptop");

    }


    // public Optional<ProductResponseDTO> getById(int id)
    @Test
    @DisplayName("getById should return productResponseDTO")
    void getById() {
        //Arrange
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        doReturn(dto).when(service).toResponseDTO(entity);
        //act
        Optional<ProductResponseDTO> result = service.getById(1);

        //assert
        assertTrue(result.isPresent());
        assertEquals("Laptop", result.get().getName());

        verify(repository, times(2)).findById(1);
        verify(service,times(1)).toResponseDTO(entity);

    }
}