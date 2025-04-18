package com.dw.jdbcapp.repository.jdbc;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.repository.iface.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerJdbcRepository implements CustomerRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // 고객 테이블 전부를 가져오는 API
    @Override
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

    // 12.20 과제.1  평균마일리지보다 큰 마일리지를 가진 고객들을 조회하는 API
    @Override
    public List<Customer> getCustomersWithHighMileThanAvg() {
        return List.of();
    }

    //12.20 과제2  마일리지등급을 매개변수로 해당 마일리지등급을 가진 고객들을 조회하는 API
    @Override
    public List<Customer> getCustomersByMileageGrade(String gradeName) {
        return List.of();
    }
}
