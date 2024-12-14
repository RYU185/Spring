package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.Port;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/find-all-products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    
    // 12/13 과제
    @GetMapping("/products/{productNumber}")
    public Product getProductNumber (@PathVariable String productNumber){
        return productService.getProductNumber(productNumber);
    }
}
