package com.dw.jdbcapp.repository.jdbc;


import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderJdbcRepository implements OrderRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        String query = "select * from 주문";

        try (Connection connection = DriverManager.getConnection(
                URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("데이터베이스 연결 성공");

            while (resultSet.next()){
                Order order = new Order();
                order.setOrderID(resultSet.getString("주문번호"));
                order.setCustomerID(resultSet.getString("고객번호"));
                order.setEmployeeID(resultSet.getString("사원번호"));
                order.setOrderDate(resultSet.getDate("주문일").toLocalDate());
                order.setRequestDate(resultSet.getDate("요청일").toLocalDate());
                order.setShippingDate(resultSet.getDate("발송일").toLocalDate());
                orders.add(order);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getOrderNumber(String orderNumber){
        Order order = new Order();
        String query = "select * from 주문 where 주문번호 = ?";

        try (
                Connection connection = DriverManager.getConnection(
                        URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            System.out.println("데이터베이스 연결 성공");
            ps.setString(1, orderNumber);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    order.setOrderID(rs.getString("주문번호"));
                    order.setCustomerID(rs.getString("고객번호"));
                    order.setEmployeeID(rs.getString("사원번호"));
                    order.setOrderDate(rs.getDate("주문일").toLocalDate());
                    order.setRequestDate(rs.getDate("요청일").toLocalDate());
                    order.setShippingDate(rs.getDate("발송일").toLocalDate());
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> getOrderByIdAndCustomer(int productNumber, String customerId) {
        List<Order> orders = new ArrayList<>();
        String query = "select * from 주문 where 고객번호 = ? and" +
                " 주문번호 in (select 주문번호 from 주문세부 where 제품번호 = ?)";
        try (
                Connection connection = DriverManager.getConnection(
                        URL, USER, PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            System.out.println("데이터베이스 연결 성공");
            pstmt.setString(1, customerId);
            pstmt.setInt(2, productNumber);
            try(ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setOrderID(resultSet.getString("주문번호"));
                    order.setCustomerID(resultSet.getString("고객번호"));
                    order.setEmployeeID(resultSet.getString("사원번호"));
                    order.setOrderDate(LocalDate.parse(resultSet.getString("주문일")));
                    order.setRequestDate(LocalDate.parse(resultSet.getString("요청일")));
                    order.setShippingDate(LocalDate.parse(resultSet.getString("발송일")));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}

