package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.DTO.OrderRequestDTO;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    
    // 주문번호, 발송일을 매개변수로 해당 주문의 발송일을 수정하는 API
    @PutMapping("/orders/update")
    public ResponseEntity<Order> updateOrderWithShippingDate (
            @RequestBody String id, @RequestBody String date){
    return new ResponseEntity<>(orderService.updateOrderWithShippingDate(id,date),
            HttpStatus.OK);
    }
    
    // 5. 도시별로 주문금액합 결과를 내림차순 정렬하여 조회하는 API
    @GetMapping("/orders/city/orderamount/{limit}")
    public ResponseEntity<List<Map<String,Double>>> getTopCitiesByTotalOrderAmount(@PathVariable int limit){
        return new ResponseEntity<>(
                orderService.getTopCitiesByTotalOrderAmount(limit),
                HttpStatus.OK);
    }
    
    @GetMapping("/orders/ordercount/year/{city}")
    public ResponseEntity <List<Map<String, Double>>> getOrderCountByYearForCity(@PathVariable String city){
        return new ResponseEntity<>(
                orderService.getOrderCountByYearForCity(city),
                HttpStatus.OK
        );
    }
}
