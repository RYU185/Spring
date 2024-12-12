package com.dw.jdbcapp.repository;

import com.dw.jdbcapp.model.Employee;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "select * from 사원";
        try (Connection connection = DriverManager.getConnection(
                URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("데이터베이스 연결 성공");

            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setEmployeeNumber(resultSet.getString("사원번호"));
                employee.setEmployeeID(resultSet.getString("이름"));
                employee.setEnglishName(resultSet.getString("영문이름"));
                employee.setEmployeePosition(resultSet.getString("직위"));
                employee.setGender(resultSet.getString("성별"));
                employee.setBirthday(resultSet.getDate("생일").toLocalDate());
                employee.setJoinDate(resultSet.getDate("입사일").toLocalDate());
                employee.setEmployeeAddress(resultSet.getString("주소"));
                employee.setCity(resultSet.getString("도시"));
                employee.setRegion(resultSet.getString("지역"));
                employee.setHomeCall(resultSet.getString("집전화"));
                employee.setSuperiorNumber(resultSet.getString("상사번호"));
                employee.setDepartmentNumber(resultSet.getString("부서번호"));
                employees.add(employee);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return employees;
    }
}
