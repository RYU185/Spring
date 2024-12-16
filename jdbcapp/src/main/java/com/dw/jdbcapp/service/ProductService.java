package com.dw.jdbcapp.service;

import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }

    public Product getProductNumber(String productNumber){
        return productRepository.getProductNumber(productNumber);
    }

    public Product saveProduct(Product product){
        return productRepository.saveProduct(product);
    }

    public List<Product> saveProductList(List<Product> productList){
        for (Product data : productList){
            productRepository.saveProduct(data);
        }
        return productList;
    }

    public Product updateProduct(Product product){
        return productRepository.updateProduct(product);
    }

    public Product deleteProduct(Product productNumber){
        return productRepository.deleteProduct(productNumber);
    }
}
