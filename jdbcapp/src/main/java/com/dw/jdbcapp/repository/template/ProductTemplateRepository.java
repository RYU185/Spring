package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductTemplateRepository implements ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    private final RowMapper<Product> productRowMapper = new RowMapper<Product>() {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setProductNumber(rs.getInt("제품번호"));
            product.setProductName(rs.getString("제품명"));
            product.setPackUnit(rs.getString("포장단위"));
            product.setPrice(rs.getDouble("단가"));
            product.setInventory(rs.getInt("재고"));
            return product;
        }
    };

    @Override
    public List<Product> getAllProducts() {
        String query = "select * from 제품";
        return jdbcTemplate.query(query, productRowMapper);
    }

    @Override
    public Product getProductNumber(int productNumber) {
        String query = "select * from 제품 where 제품번호 = ?";
        try {
            return jdbcTemplate.queryForObject(query, productRowMapper, productNumber);
        } catch (EmptyResultDataAccessException e) {
            // 자바에 정의된 예외를 사용자 정의 예외로 바꿈으로 인해
            // CustomExceptionHandler의 코드를 단순하게 유지할 수 있다.
            // ( 예외들을 비슷한 유형으로 그룹화 할 수 있음 )
            throw new ResourceNotFoundException("조회된 제품번호가 올바르지 않습니다: " + productNumber);
        }
    }

    // 12/19 제품을 조회할 때 단가를 매개변수로 전달하고 해당 단가보다 싼 제품을 조회
    @Override
    public List<Product> getProductPriceBelow(double price_below) {
        String query = "select * from 제품 where 제품.단가 < ?";
        return jdbcTemplate.query(query, productRowMapper, price_below);
    }
    



    /* <jdbctemplate의 update 메서드>
    1. update(String query, 매개변수1, 매개변수2,...)
        :insert, update, delete 등을 수행할 때 사용
     */

    @Override
    public Product saveProduct(Product product) {
        String query = "insert into 제품(제품번호, 제품명, 포장단위, 단가, 재고)" +
                "values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                product.getProductNumber(),
                product.getProductName(),
                product.getPackUnit(),
                product.getPrice(),
                product.getInventory());
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        String query = "update 제품 set 제품명 = ?, 포장단위 = ?, 단가 = ?, 재고 = ? " +
                "where 제품번호 = ?";
        jdbcTemplate.update(query,
                product.getProductName(),
                product.getPackUnit(),
                product.getPrice(),
                product.getInventory(),
                product.getProductNumber());
        return product;
    }

    @Override
    public int deleteProduct(int id) {
        String query = "delete from 제품 where 제품번호 = ?";
        jdbcTemplate.update(query, id);
        return id;
    }
    
    // 8. 제품번호와 재고를 매개변수로 해당 제품의 재고를 수정하는 api
    // ???
    @Override
    public int updateProductWithStock(int id, int stock) {
        String query = "update 제품 set 재고 = ? " +
                "where 제품번호 = ?";
        return jdbcTemplate.update(query, id, stock);
    }

    // 9. 제품명의 일부를 매개변수로 해당 문자열을 포함하는 제품들을 조회하는 api
    @Override
    public List<Product> getProductByProductName(String name) {
        String query = "select * from 제품 where 제품명 like ?";
        String searchName = "%"+name+"%";
        return jdbcTemplate.query(query,productRowMapper,searchName);

    }
}
