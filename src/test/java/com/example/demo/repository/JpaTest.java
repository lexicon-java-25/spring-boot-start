package com.example.demo.repository;

import com.example.demo.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ActiveProfiles("test")
@DataJpaTest
public class JpaTest {

    @Autowired
    private ProductRepository productRepository;

//    findByPriceBetween(Double min, Double max);

    @Test
    @DisplayName("findByPriceBetween Should Return Correct")
    void findByPriceBetweenShouldReturnCorrect(){
        //arrange / given
        Product p1 = new Product();
        p1.setName("prod1");
        p1.setPrice(10);

        Product p2 = new Product();
        p2.setName("prod2");
        p2.setPrice(20);

        Product p3 = new Product();
        p3.setName("prod3");
        p3.setPrice(30);

        Product p4 = new Product();
        p4.setName("prod4");
        p4.setPrice(9990);

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);

        //act / when
        List<Product> result = productRepository.findByPriceBetween(11.0,9980.0);

        //assert / then
        assertEquals(2,result.size());
        assertEquals(result.get(0).getName(),"prod2");
    }




}
