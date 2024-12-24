package com.dw.jdbcapp.service;

import com.dw.jdbcapp.DTO.OrderRequestDTO;
import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.model.OrderDetail;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.OrderDetailRepository;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    @Qualifier("orderTemplateRepository")
    OrderRepository orderRepository;
    @Autowired
    @Qualifier("orderDetailTemplateRepository")
    OrderDetailRepository orderDetailRepository; // 주문세부 Repository에 insert해야하므로 의존성 주입
                                                 // Service는 Repository에 관해 얽매이지 않는다

    @Autowired
    @Qualifier("productTemplateRepository")
    ProductRepository productRepository;

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


    // 선언된 메서드 수행도중 예외가 발생하면 이미 수행되었던 동작을
    // 모두 롤백(rollback = 원상복귀) 시키도록 명령하는 어노테이션
    // 주문세부의 특정 제품의 재고가 부족하여 예외가 발생하면 전체주문, 주문세부의
    // 저장되었던 내용들은 모두 취소되고 롤백됨
    @Transactional
    public OrderRequestDTO saveOrder(OrderRequestDTO orderRequestDTO) {
        // 1. DTO에서 주문정보를 꺼내 주문테이블에 insert
        orderRepository.saveOrders(orderRequestDTO.toOrder());
        // 2. DTO에서 주문세부정보를 꺼내 주문세부테이블에 insert. 반복문필요
        for (OrderDetail data : orderRequestDTO.getOrderDetails()) {
            // 과제 4-7 주문입력 API에서 아래 예외를 추가하시오
            // 제품테이블의 재고보다 많은 양을 주문하는 경우 예외발생
            Product product = productRepository.getProductNumber(
                    data.getProductID());
            System.out.println(product.getInventory() + " " + data.getOrderCount());
            if (product.getInventory() - data.getOrderCount() < 0) {
                throw new InvalidRequestException(
                        "요청하신 수량은 현재 재고를 초과합니다: " +
                                product.getProductName() + ", 현재 재고 " +
                                product.getInventory());
            }
            orderDetailRepository.saveOrderDetail(data);
        }
        return orderRequestDTO;
    }

    // 12월20일금요일_과제4 4. 주문번호, 발송일을 매개변수로 해당 주문의 발송일을 수정하는 API
    public String updateOrderWithShippingDate(String id, String date) {
        orderRepository.updateOrderWithShippingDate(id, date);
        return " 성공적으로 수정하였습니다 ";
    }
    
    public List<Map<String,Double>> getTopCitiesByTotalOrderAmount(int limit){
        return orderRepository.getTopCitiesByTotalOrderAmount(limit);
    }
    
    public List<Map<String,Double>> getOrderCountByYearForCity(String city){
        return orderRepository.getOrderCountByYearForCity(city);
    }
}
