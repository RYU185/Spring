package com.dw.dynamic.repository;

import com.dw.dynamic.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,String> {
    Optional<Category> findByName(String name);
}
