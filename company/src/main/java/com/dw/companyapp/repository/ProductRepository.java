package com.dw.companyapp.repository;

import com.dw.companyapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByProductId(int productNumber);
}
