package com.dw.jdbcapp.repository.jdbc;

import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductJdbcRepository implements ProductRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
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
                product.setProductNumber(resultSet.getInt("제품번호"));
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

    @Override
    public Product getProductNumber(int productNumber){
        Product product = new Product();
        String query = "select * from 제품 where 제품번호 = ?";

        try (
                Connection connection = DriverManager.getConnection(
                        URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            System.out.println("데이터베이스 연결 성공");
            ps.setInt(1, productNumber);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    product.setProductNumber(rs.getInt("제품번호"));
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

    // 12/19 제품을 조회할 때 단가를 매개변수로 전달하고 해당 단가보다 싼 제품을 조회
    @Override
    public List<Product> getProductPriceBelow(double price_below){
        List<Product> products = new ArrayList<>();
        String query = "select * from 제품 where 제품.단가 < ?";
        try (
                Connection connection = DriverManager.getConnection(
                        URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            System.out.println("데이터베이스 연결 성공");
            ps.setDouble(1, price_below);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductNumber(rs.getInt("제품번호"));
                    product.setProductName(rs.getString("제품명"));
                    product.setPackUnit(rs.getString("포장단위"));
                    product.setPrice(rs.getDouble("단가"));
                    product.setInventory(rs.getInt("재고"));
                    products.add(product);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    @Override
    public int updateProductWithStock(int id, int stock) {
        return 0;
    }

    // 9. 제품명의 일부를 매개변수로 해당 문자열을 포함하는 제품들을 조회하는 api
    @Override
    public List<Product> getProductByProductName(String name) {
        return List.of();
    }

    @Override
    public Product saveProduct(Product product){
        String query = "insert into 제품(제품번호, 제품명, 포장단위, 단가, 재고) "
                +"values (?, ?, ?, ?, ?)";
        try(
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, product.getProductNumber());
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

    @Override
    public Product updateProduct(Product product){
        String query = "update 제품 set 제품명 = ?, 포장단위 = ?, 단가 = ?, 재고 = ? " +
                "where 제품번호 = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getPackUnit());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getInventory());
            pstmt.setInt(5, product.getProductNumber());
            pstmt.executeUpdate();
            System.out.println("Update 성공");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    public int deleteProduct(int id) {
        String query = "delete from 제품 where 제품번호 = ?";
        try(Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("DELETE 성공");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
