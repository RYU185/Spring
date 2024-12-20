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
}
