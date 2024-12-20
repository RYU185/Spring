package com.dw.jdbcapp.DTO;

import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.model.OrderDetail;

import java.time.LocalDate;
import java.util.List;

public class OrderRequestDTO {
    private String orderId;
    private String customerId;
    private String employeeId;
    private LocalDate requestDate;
    private List<OrderDetail> OrderDetails;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(String orderId, String customerId, String employeeId, LocalDate requestDate, List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.requestDate = requestDate;
        OrderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public List<OrderDetail> getOrderDetails() {
        return OrderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        OrderDetails = orderDetails;
    }

    public Order toOrder(){  // Service 의 할일을 덜어주고 유지보수 관리에 편함
                             // 주문 쪽에 들어갈 것들만 모아서 메서드로
                             // 클라이언트에서 서버로 들어가는 경우의 코드 정리스킬
        Order order = new Order();
        order.setOrderID(this.orderId);
        order.setCustomerID(this.customerId);
        order.setEmployeeID(this.employeeId);
        order.setOrderDate(LocalDate.now());
        order.setRequestDate(this.requestDate);
        return order;
    }
}
