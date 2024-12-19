package com.dw.jdbcapp.controller;


import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/find-all-customer")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(
                customerService.getAllCustomers(), // 첫번째 매개변수는 데이터
                HttpStatus.OK); // 두번째는 http의 상태

    }
}
