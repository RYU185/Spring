package com.dw.jdbcapp.repository;

import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.model.OrderDetail;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDetailRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public List<OrderDetail> getAllOrderDetails(){
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "select * from 주문세부";

        try (Connection connection = DriverManager.getConnection(
                URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("데이터베이스 연결 성공");

            while (resultSet.next()){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderID(resultSet.getString("주문번호"));
                orderDetail.setProductID(resultSet.getString("제품번호"));
                orderDetail.setPrice(resultSet.getInt("단가"));
                orderDetail.setOrderCount(resultSet.getInt("주문수량"));
                orderDetail.setSaleRate(resultSet.getDouble("할인율"));
                orderDetails.add(orderDetail);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return orderDetails;
    }

}
