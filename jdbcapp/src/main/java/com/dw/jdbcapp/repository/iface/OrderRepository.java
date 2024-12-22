package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

public interface OrderRepository {
    List<Order> getAllOrders();

    Order getOrderNumber(String orderNumber);

    List<Order> getOrderByIdAndCustomer(int productNumber, String customerId);

    // jdbc의 update 리턴값은 int
    // 행의 '갯수'이기 때문에
    int saveOrders(Order order);

    Order updateOrderWithShippingDate(String id, String date);
    
    List<Map<String, Double>> getTopCitiesByTotalOrderAmount(int limit);
    
    List<Map<String, Double>> getOrderCountByYearForCity(String city);
}
