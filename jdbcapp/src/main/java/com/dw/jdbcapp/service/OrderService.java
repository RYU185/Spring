package com.dw.jdbcapp.service;

import com.dw.jdbcapp.DTO.OrderRequestDTO;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.model.OrderDetail;
import com.dw.jdbcapp.repository.iface.OrderDetailRepository;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    @Qualifier("orderTemplateRepository")
    OrderRepository orderRepository;
    @Autowired
    @Qualifier("orderDetailTemplateRepository")
    OrderDetailRepository orderDetailRepository; // 주문세부 Repository에 insert해야하므로 의존성 주입
                                                 // Service는 Repository에 관해 얽매이지 않는다

    public List<Order> getAllOrders(){
        return orderRepository.getAllOrders();
    }
    public Order getOrderNumber (String orderNumber){
        return orderRepository.getOrderNumber(orderNumber);
    }

    public List<Order> getOrderByIdAndCustomer(int productNumber, String customerId) {
        List<Order> orders = orderRepository.getOrderByIdAndCustomer(productNumber, customerId);
        if (orders.isEmpty()){
            throw new ResourceNotFoundException("조회된 주문정보가 없습니다: "+productNumber+":"+customerId);
        }
        return orders;
    }

    public OrderRequestDTO saveOrder(OrderRequestDTO orderRequestDTO){
        // 1. DTO에서 주문정보를 꺼내 주문테이블에 저장(INSERT)
        orderRepository.saveOrders(orderRequestDTO.toOrder());
        // 2. DTO에서 주문세부정보를 꺼내 주문세부테이블에 저장(INSERT) - 반복문 필요 (DTO 필드의 주문세부는 List 형태이므로)
        for (OrderDetail data : orderRequestDTO.getOrderDetails()) {
            orderDetailRepository.saveOrderDetail(data);
        }
        return orderRequestDTO;
    }
    
    public Order updateOrderWithShippingDate(String id, String date) {
        return orderRepository.updateOrderWithShippingDate(id, date);
    }
}
