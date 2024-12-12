package com.dw.jdbcapp.model;

import java.time.LocalDate;

public class Order {
    String orderID;
    String customerID;
    String employeeID;
    LocalDate orderDate;
    LocalDate requestDate;
    LocalDate shippingDate;

    public Order() {
    }

    public Order(String orderID, String customerID, String employeeID, LocalDate orderDate, LocalDate requestDate, LocalDate shippingDate) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.orderDate = orderDate;
        this.requestDate = requestDate;
        this.shippingDate = shippingDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", employeeID='" + employeeID + '\'' +
                ", orderDate=" + orderDate +
                ", requestDate=" + requestDate +
                ", shippingDate=" + shippingDate +
                '}';
    }
}
