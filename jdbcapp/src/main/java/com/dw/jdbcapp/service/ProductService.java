package com.dw.jdbcapp.service;

import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import com.dw.jdbcapp.repository.jdbc.ProductJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    @Qualifier("productTemplateRepository") // 아래에 주입될 구현체를 지정해주는 어노테이션
                                            // JDBC로 사용하고싶다면 productJdbcRepository로 수정
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }

    public Product getProductNumber(int productNumber){
        if(productNumber < 0 ){
            throw new InvalidRequestException("존재하지 않는 제품번호입니다: "
                    + productNumber);
        }
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

    public int deleteProduct(int id) {
        return productRepository.deleteProduct(id);
    }

    // 12/19 과제
    public List<Product> getProductPriceBelow(double price_below) {
        List<Product> products = productRepository.getProductPriceBelow(price_below);
            if(products.isEmpty()){
               throw new ResourceNotFoundException("해당되는 제품이 없습니다");
        }
        return products;
    }
    
    public String updateProductWithStock(int id, int stock) {
        return productRepository.updateProductWithStock(id, stock);
    }
}
