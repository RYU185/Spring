package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
}
