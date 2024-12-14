package com.dw.jdbcapp.controller;


import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/find-all-customer")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    
    // 12/13 과제
    @GetMapping("/customers/{productNumber}/{customerId}")
    public List<Customer> getCustomerWithPdNumId(@PathVariable String productNumber,@PathVariable String customerId){
        return customerService.getCustomerWithPdNumId(productNumber,customerId);
    }
}
