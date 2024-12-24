package com.dw.jdbcapp.service;

import com.dw.jdbcapp.DTO.ProductDTO;
import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Qualifier("productTemplateRepository") // 아래에 주입될 구현체를 지정해주는 어노테이션
                                            // JDBC로 사용하고싶다면 productJdbcRepository로 수정

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
        productRepository.updateProductWithStock(id, stock);
        return "성공적으로 수정하였습니다";
    }

    // 9. 제품명의 일부를 매개변수로 해당 문자열을 포함하는 제품들을 조회하는 api
    public List<Product> getProductByProductName(String name){
        return productRepository.getProductByProductName(name);
    }

    // 10. ProductDTO를 아래 형식으로 추가하고 조회하는 API
    public List<ProductDTO> getProductsByStockValue(){
        List<Product> products = productRepository.getAllProducts();
        List<ProductDTO> productArrayList = new ArrayList<>();
        for (Product data : products){
           // productArrayList.add(ProductDTO.fromProduct(data));
            productArrayList.add(new ProductDTO(data));
        }
        return productArrayList;
    }
}
