package com.dw.jdbcapp.repository;

import com.dw.jdbcapp.model.Employee;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

@Repository
public class EmployeeRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public List<Employee> getAllEmployees(){
        List<Employee> employees = new ArrayList<>();
        String query = "select * from 사원";
        try (Connection connection = DriverManager.getConnection(
                URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("데이터베이스 연결 성공");
            while (resultSet.next()) {
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

            } catch (SQLException e){
            e.printStackTrace();
        }return employees;
    }

    public Employee getEmployeeById(String id) {
        Employee employee = new Employee();
        String query = "select * from 사원 where 사원번호 = ?";
        try (
                Connection connection = DriverManager.getConnection(
                        URL, USER, PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            System.out.println("데이터베이스 연결 성공");
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    employee.setEmployeeNumber(rs.getString("사원번호"));
                    employee.setEmployeeID(rs.getString("이름"));
                    employee.setEnglishName(rs.getString("영문이름"));
                    employee.setEmployeePosition(rs.getString("직위"));
                    employee.setGender(rs.getString("성별"));
                    employee.setBirthday(rs.getDate("생일").toLocalDate());
                    employee.setJoinDate(rs.getDate("입사일").toLocalDate());
                    employee.setEmployeeAddress(rs.getString("주소"));
                    employee.setCity(rs.getString("도시"));
                    employee.setRegion(rs.getString("지역"));
                    employee.setHomeCall(rs.getString("집전화"));
                    employee.setSuperiorNumber(rs.getString("상사번호"));
                    employee.setDepartmentNumber(rs.getString("부서번호"));
                         }
                    }
                }catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(employee);
        return employee;
    }


    public List<Map<String, Object>> getEmployeesWithDepartName(){
        String query = "select 이름, 입사일, 부서명 from 사원 "
                + "inner join 부서 on 사원.부서번호 = 부서.부서번호";
        List<Map<String, Object>> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(
                URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            while(resultSet.next()){
                Map<String, Object> employee = new HashMap<>();
                employee.put("이름",resultSet.getString("이름"));
                employee.put("입사일",resultSet.getString("입사일"));
                employee.put("부서명",resultSet.getString("부서명"));
                employees.add(employee);
            }
            for(Map<String, Object> employee : employees){
                System.out.println(employee);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<Employee> getEmployeeWithDepartPosition(String departmentNumber, String employeePosition) {
        List<Employee> employees = new ArrayList<>();
        String query = "select * from 사원 where 부서번호 = ? and 직위 = ?";
        try (
                Connection connection = DriverManager.getConnection(
                        URL, USER, PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            System.out.println("데이터베이스 연결 성공");
            pstmt.setString(1, departmentNumber);
            pstmt.setString(2, employeePosition);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeNumber(rs.getString("사원번호"));
                    employee.setEmployeeID(rs.getString("이름"));
                    employee.setEnglishName(rs.getString("영문이름"));
                    employee.setEmployeePosition(rs.getString("직위"));
                    employee.setGender(rs.getString("성별"));
                    employee.setBirthday(rs.getDate("생일").toLocalDate());
                    employee.setJoinDate(rs.getDate("입사일").toLocalDate());
                    employee.setEmployeeAddress(rs.getString("주소"));
                    employee.setCity(rs.getString("도시"));
                    employee.setRegion(rs.getString("지역"));
                    employee.setHomeCall(rs.getString("집전화"));
                    employee.setSuperiorNumber(rs.getString("상사번호"));
                    employee.setDepartmentNumber(rs.getString("부서번호"));
                    employees.add(employee);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
       return employees;
    }
}
