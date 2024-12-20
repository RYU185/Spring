package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.DTO.OrderRequestDTO;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/find-all-orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(
                orderService.getAllOrders(),
                HttpStatus.OK);
    }
    
    
    // 12/13 과제
    @GetMapping("/order/{orderNumber}")
    public ResponseEntity<Order> getOrderNumber(@PathVariable String orderNumber){
        return new ResponseEntity<>(
                orderService.getOrderNumber(orderNumber),
                HttpStatus.OK);
    }
    
    @GetMapping("/order?orderNumber")
    public ResponseEntity<Order> getOrderNumber_2(@RequestParam String orderNumber){
        return new ResponseEntity<>(
                orderService.getOrderNumber(orderNumber),
                HttpStatus.OK);
    }

    @GetMapping("/orders/{productNumber}/{customerId}")
    public ResponseEntity<List<Order>> getOrderByIdAndCustomer(@PathVariable int productNumber,
                                               @PathVariable String customerId) {
        return new ResponseEntity<>(
                orderService.getOrderByIdAndCustomer(productNumber,customerId),
                HttpStatus.OK
        );
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderRequestDTO> saveOrder(
            @RequestBody OrderRequestDTO orderRequestDTO) {
        return new ResponseEntity<>(
                orderService.saveOrder(orderRequestDTO),
                HttpStatus.CREATED
        );
    }
}
