package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAllOrders();

    Order getOrderNumber(String orderNumber);

    List<Order> getOrderByIdAndCustomer(int productNumber, String customerId);

    // jdbc의 update 리턴값은 int
    // 행의 '갯수'이기 때문에
    int saveOrders(Order order);


}
