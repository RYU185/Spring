package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.List;

@RestController
@RequestMapping("/api")
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
    
    @GetMapping("/products?productNumber")
    public Product getProductNumber_2(@RequestParam String productNumber){
        return productService.getProductNumber(productNumber);
    }

    // 12/16 과제
    // #1
    @PostMapping("/post/product")
    public Product saveProduct (@RequestBody Product product){
        return productService.saveProduct(product);
    }

    // #2
    @PostMapping("/post/productlist")
    public List <Product> saveProductList(@RequestBody List<Product> productList ){
        return productService.saveProductList(productList);
    }

    // #4
    @PutMapping("/put/product")
    public Product updateProduct (@RequestBody Product product){
        return productService.updateProduct(product);
    }

    // #5.
    @DeleteMapping("/delete/product")
    public String deleteProduct(@RequestParam int id) {
        return "제품번호: " + productService.deleteProduct(id) + " 삭제됨";

    }
}
