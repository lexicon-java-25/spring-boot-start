package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductRequestDTO;
import com.example.demo.model.dto.ProductResponseDTO;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService service;

    private final OrderService orderService;

    public ProductController(ProductService service, OrderService orderService) {
        this.service = service;
        this.orderService = orderService;
    }


    @GetMapping("/order")
    public ResponseEntity<List<Order>> getOrderByUser(){
        return ResponseEntity.ok(orderService.searchOrderForCurrentUser());

    }
    @GetMapping
    public List<ProductResponseDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/category")
    public List<ProductResponseDTO> getByCategory(@RequestParam String name){
        return service.findByCategory(name);
    }

    @GetMapping("/price")
    public List<ProductResponseDTO> getByPriceBetween(@RequestParam Double min, @RequestParam Double max){
        return service.findByPriceBetween(min,max);
    }

    //localhost:port/products/3
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getByid(@PathVariable int id){
        return service.getById(id)
                .map(p -> ResponseEntity.status(200).body(p))
                .orElse(ResponseEntity.status(418).build());
    }


    //localhost:port/products/search?name=banana
    @GetMapping("/search")
    public List<ProductResponseDTO> search(@RequestParam
                                    @NotBlank
                                    @Size(min = 2, message ="query must be at least 2 characters long")
                                    String query){
        return service.searchByName(query);
    }

    @GetMapping("/find")
    public List<ProductResponseDTO> find(@RequestParam
                                           @NotBlank
                                           @Size(min = 2, message ="query must be at least 2 characters long")
                                           String query){
        return service.findByName(query);
    }

    @GetMapping("/custom")
    public List<ProductResponseDTO> findCustom(@RequestParam
                                         @NotBlank
                                         @Size(min = 2, message ="query must be at least 2 characters long")
                                         String query){
        return service.findCustom(query);
    }


    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO request){

        ProductResponseDTO response = service.addProduct(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        boolean removed = service.deleteById(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestParam String name, @RequestParam double price){
//        Product result = service.updateProduct(id, new Product(-1,name,price));
//        if (result != null){
//            return ResponseEntity.ok(result);
//        } else{
//            return ResponseEntity.notFound().build();
//        }
//
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable int id, @RequestBody ProductRequestDTO productDTO){
        ProductResponseDTO result = service.updateProduct(id, productDTO);
        if (result != null){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.notFound().build();
        }

    }


}
