package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/find-all-orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
    
    
    // 12/13 과제
    @GetMapping("/order/{orderNumber}")
    public Order getOrderNumber(@PathVariable String orderNumber){
        return orderService.getOrderNumber(orderNumber);
    }
    
    @GetMapping("/order?orderNumber")
    public Order getOrderNumber_2(@RequestParam String orderNumber){
        return  orderService.getOrderNumber(orderNumber);
    }

    @GetMapping("/orders/{productNumber}/{customerId}")
    public List<Order> getOrderByIdAndCustomer(@PathVariable int productNumber,
                                               @PathVariable String customerId) {
        return orderService.getOrderByIdAndCustomer(productNumber, customerId);
    }
}
