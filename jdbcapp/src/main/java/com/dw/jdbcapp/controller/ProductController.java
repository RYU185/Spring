package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.DTO.ProductDTO;
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
    public ResponseEntity <Product> getProductNumber (@PathVariable int productNumber){
        return new ResponseEntity<>(
                productService.getProductNumber(productNumber),
                HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/products?productNumber")
    public ResponseEntity <Product> getProductNumber_2(@RequestParam int productNumber){
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

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductPriceBelow(@RequestParam double price_below){
        return new ResponseEntity<>(
                productService.getProductPriceBelow(price_below),
                HttpStatus.OK);
    }

    // 8. 제품번호와 재고를 매개변수로 해당제품의 재고를 수정하는 api
    @PutMapping("/products/update")
    public ResponseEntity<Product> updateProductWithStock(@RequestBody Product product){
        return new ResponseEntity<>(
                productService.updateProductWithStock(product),
                HttpStatus.OK
        );
    }

    // 9. 제품명의 일부를 매개변수로 해당 문자열을 포함하는 제품들을 조회하는 api
    @GetMapping("/products/name/{name}")
    public ResponseEntity<List<Product>> getProductByProductName (@PathVariable String name){
        return new ResponseEntity<>(
                productService.getProductByProductName(name),
                HttpStatus.OK
        );
    }

    // 10. ProductDTO를 아래 형식으로 추가하고 조회하는 API
    @GetMapping("/products/stockvalue")
    public ResponseEntity <List<ProductDTO>> getProductsByStockValue(){
        return new ResponseEntity<>(
                productService.getProductsByStockValue,
                HttpStatus.OK
        );
    }
}
