package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderTemplateRepository implements OrderRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Order> orderRowMapper = new RowMapper<Order>() {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setOrderID(rs.getString("주문번호"));
            order.setCustomerID(rs.getString("고객번호"));
            order.setEmployeeID(rs.getString("사원번호"));
            order.setOrderDate(rs.getDate("주문일").toLocalDate());
            order.setRequestDate(rs.getDate("요청일").toLocalDate());
            order.setShippingDate(rs.getDate("발송일").toLocalDate());
            return order;
        }
    };

    @Override
    public List<Order> getAllOrders() {
        String query = "select * from 주문";
        return jdbcTemplate.query(query, orderRowMapper);
    }

    @Override
    public Order getOrderNumber(String orderNumber) {
        String query = "select * from 주문 where 주문번호 = ?";
        try {
            return jdbcTemplate.queryForObject(query, orderRowMapper, orderNumber);
        }catch (EmptyResultDataAccessException e){
           throw new InvalidRequestException("주문번호가 올바르지 않습니다: "+orderNumber);
        }
    }

    @Override
    public List<Order> getOrderByIdAndCustomer(int productNumber, String customerId) {
        String query = "select * from 주문 where 고객번호 = ? and" +
                " 주문번호 in (select 주문번호 from 주문세부 where 제품번호 = ?)";
            return jdbcTemplate.query(query, orderRowMapper, productNumber, customerId);

    }
}
