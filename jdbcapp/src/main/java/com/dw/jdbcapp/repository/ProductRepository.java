package com.dw.jdbcapp.repository;

import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        String query = "select * from 제품";

        try (Connection connection = DriverManager.getConnection(
                URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("데이터베이스 연결 성공");

            while (resultSet.next()){
                Product product = new Product();
                product.setProductNumber(resultSet.getString("제품번호"));
                product.setProductName(resultSet.getString("제품명"));
                product.setPackUnit(resultSet.getString("포장단위"));
                product.setPrice(resultSet.getDouble("단가"));
                product.setInventory(resultSet.getInt("재고"));
                products.add(product);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductNumber(String productNumber){
        Product product = new Product();
        String query = "select * from 제품 where 제품번호 = ?";

        try (
                Connection connection = DriverManager.getConnection(
                        URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            System.out.println("데이터베이스 연결 성공");
            ps.setString(1, productNumber);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    product.setProductNumber(rs.getString("제품번호"));
                    product.setProductName(rs.getString("제품명"));
                    product.setPackUnit(rs.getString("포장단위"));
                    product.setPrice(rs.getDouble("단가"));
                    product.setInventory(rs.getInt("재고"));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }


    public Product saveProduct(Product product){
        String query = "insert into 제품(제품번호, 제품명, 포장단위, 단가, 재고) "
                +"values (?, ?, ?, ?, ?)";
        try(
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, product.getProductNumber());
            pstmt.setString(2, product.getProductName() );
            pstmt.setString(3, product.getPackUnit() );
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getInventory());
            pstmt.executeUpdate();
            System.out.println("Insert 성공");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    public Product updateProduct(Product product){
        String query = "update 제품 set 제품명 = ?, 포장단위 = ?, 단가 = ?, 재고 = ? " +
                "where 제품번호 = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getPackUnit());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getInventory());
            pstmt.setString(5, product.getProductNumber());
            pstmt.executeUpdate();
            System.out.println("Update 성공");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    public Product deleteProduct(Product productNumber){
        String query = "delete from 제품 where 제품번호 = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, productNumber.getProductNumber());
            pstmt.executeUpdate();
            System.out.println("delete 성공");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return productNumber;
    }
}
