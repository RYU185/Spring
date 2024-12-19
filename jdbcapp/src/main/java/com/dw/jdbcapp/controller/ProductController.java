package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/find-all-products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(
                productService.getAllProducts(),
                HttpStatus.OK);
    }
    
    // 12/13 과제
    @GetMapping("/products/{productNumber}")
    public ResponseEntity <Product> getProductNumber (@PathVariable String productNumber){
        return new ResponseEntity<>(
                productService.getProductNumber(productNumber),
                HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/products?productNumber")
    public ResponseEntity <Product> getProductNumber_2(@RequestParam String productNumber){
        return new ResponseEntity<>(
                productService.getProductNumber(productNumber),
                HttpStatus.ACCEPTED);
    }

    // 12/16 과제
    // #1
    @PostMapping("/post/product")
    public ResponseEntity <Product> saveProduct (@RequestBody Product product){
        return new ResponseEntity<>(
                productService.saveProduct(product),
                HttpStatus.CREATED);
    }

    // #2
    @PostMapping("/post/productlist")
    public ResponseEntity <List <Product>> saveProductList(@RequestBody List<Product> productList ){
        return new ResponseEntity<>(
                productService.saveProductList(productList),
                HttpStatus.CREATED);
    }

    // #4
    @PutMapping("/put/product")
    public ResponseEntity <Product> updateProduct (@RequestBody Product product){
        return new ResponseEntity<>(
                productService.updateProduct(product),
                HttpStatus.OK);

    }

    // #5.
    @DeleteMapping("/delete/product")
    public ResponseEntity <String> deleteProduct(@RequestParam int id) {
        return new ResponseEntity<>(
                "제품번호: " + productService.deleteProduct(id) + " 삭제됨",
                HttpStatus.ACCEPTED);
    }
}
