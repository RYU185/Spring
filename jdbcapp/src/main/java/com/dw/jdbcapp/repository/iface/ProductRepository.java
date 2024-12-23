package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();

    Product getProductNumber(int productNumber);

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    int deleteProduct(int productNumber);

    List<Product> getProductPriceBelow(double price_below);
    
    Product updateProductWithStock(Product product);

    // 9. 제품명의 일부를 매개변수로 해당 문자열을 포함하는 제품들을 조회하는 api
    List<Product> getProductByProductName(String name);
}
