package com.dw.dynamic.controller;

import com.dw.dynamic.DTO.CategoryEnrollmentAndIncomeDTO;
import com.dw.dynamic.DTO.CourseEnrollmentAndIncomeDTO;
import com.dw.dynamic.DTO.PayrollSubscriptionsEnrollmentAndIncomeDTO;
import com.dw.dynamic.DTO.ProductDTO;
import com.dw.dynamic.model.Course;
import com.dw.dynamic.model.Product;
import com.dw.dynamic.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductDTO> getProductsById(@PathVariable String id){
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<ProductDTO>> getProductsByTitle(@PathVariable String title){
        return new ResponseEntity<>(productService.getProductsByTitle(title), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO, HttpServletRequest request){
        return new ResponseEntity<>(productService.saveProduct(productDTO, request), HttpStatus.CREATED);
    }

    @GetMapping("payrollsubscription/enrollments-and-income")
    public ResponseEntity<List<PayrollSubscriptionsEnrollmentAndIncomeDTO>> getPayrollSubscriptionsEnrollmentsAndIncomes(HttpServletRequest request){
        return new ResponseEntity<>(
                productService.getPayrollSubscriptionsEnrollmentsAndIncomes(request),
                HttpStatus.OK
        );
    }

    @GetMapping("category/enrollments-and-income")
    public ResponseEntity<List<CategoryEnrollmentAndIncomeDTO>> getCategoryEnrollmentsAndIncomes(HttpServletRequest request){
        return new ResponseEntity<>(
                productService.getCategoryEnrollmentsAndIncomes(request),
                HttpStatus.OK
        );
    }

    @GetMapping("courses/enrollments-and-income")
    public ResponseEntity<List<CourseEnrollmentAndIncomeDTO>> getCoursesEnrollmentsAndIncomes(HttpServletRequest request){
        return new ResponseEntity<>(
                productService.getCoursesEnrollmentsAndIncomes(request),
                HttpStatus.OK
        );
    }
}
