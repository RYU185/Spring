package com.dw.jdbcapp.repository;

import com.dw.jdbcapp.model.Customer;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "select * from 고객";
        try (
                Connection connection = DriverManager.getConnection(
                        URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("데이터베이스 연결 성공");

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerNumber(resultSet.getString("고객번호"));
                customer.setCustomerCompany(resultSet.getString("고객회사명"));
                customer.setManagerName(resultSet.getString("담당자명"));
                customer.setManagerPosition(resultSet.getString("담당자직위"));
                customer.setAddress(resultSet.getString("주소"));
                customer.setCity(resultSet.getString("도시"));
                customer.setRegion(resultSet.getString("지역"));
                customer.setPhone(resultSet.getString("전화번호"));
                customer.setMileage(resultSet.getInt("마일리지"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    
    public List<Customer> getCustomerWithPdNumId (String productNumber, String customerId) {
        List<Customer> customers = new ArrayList<>();
        String query =
                "select * from 고객 " +
                        "join 주문 on 고객.고객번호 = 주문.고객번호 " +
                        "join 주문세부 on 주문.주문번호 = 주문세부.주문번호 " +
                        "join 제품 on 주문세부.제품번호 = 제품.제품번호 " +
                        "where 제품.제품번호 = ? and 고객.고객번호 = ?";
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
                ps.setString(1,productNumber);
                ps.setString(2,customerId);
                System.out.println("데이터베이스 연결 성공");
                
                try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerNumber(rs.getString("고객번호"));
                    customer.setCustomerCompany(rs.getString("고객회사명"));
                    customer.setManagerName(rs.getString("담당자명"));
                    customer.setManagerPosition(rs.getString("담당자직위"));
                    customer.setAddress(rs.getString("주소"));
                    customer.setCity(rs.getString("도시"));
                    customer.setRegion(rs.getString("지역"));
                    customer.setPhone(rs.getString("전화번호"));
                    customer.setMileage(rs.getInt("마일리지"));
                    customers.add(customer);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
