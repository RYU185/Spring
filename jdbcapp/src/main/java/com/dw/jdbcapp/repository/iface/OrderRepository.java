package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderRepository {
    List<Order> getAllOrders();

    Order getOrderNumber(String orderNumber);

    List<Order> getOrderByIdAndCustomer(int productNumber, String customerId);

    // jdbc의 update 리턴값은 int
    // 행의 '갯수'이기 때문에
    int saveOrders(Order order);

    // 12월20일금요일_과제4 4. 주문번호, 발송일을 매개변수로 해당 주문의 발송일을 수정하는 API
    int updateOrderWithShippingDate(String id, String date);
    
    List<Map<String, Double>> getTopCitiesByTotalOrderAmount(int limit);
    
    List<Map<String, Double>> getOrderCountByYearForCity(String city);
}
