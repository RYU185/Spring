package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    private final RowMapper<Map<String, Double>> orderRowMapper2 = new RowMapper<Map<String, Double>>() {
        @Override
        public Map<String, Double> mapRow(ResultSet rs, int rowNum) throws SQLException {
            Map<String, Double> stringDoubleMap2 = new HashMap<>();
            stringDoubleMap2.put(rs.getString("도시"), rs.getDouble("주문금액합"));
            return stringDoubleMap2;
        }
    };
    
    private final RowMapper<Map<String, Double>> orderRowMapper3 = new RowMapper<Map<String, Double>>() {
        @Override
        public Map<String, Double> mapRow(ResultSet rs, int rowNum) throws SQLException {
            Map<String, Double> stringDoubleMap3 = new HashMap<>();
            stringDoubleMap3.put(rs.getString("주문연도"),rs.getDouble("count(*)"));
            return stringDoubleMap3;
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
        } catch (EmptyResultDataAccessException e) {
            throw new InvalidRequestException("주문번호가 올바르지 않습니다: " + orderNumber);
        }
    }

    @Override
    public List<Order> getOrderByIdAndCustomer(int productNumber, String customerId) {
        String query = "select * from 주문 where 고객번호 = ? and" +
                " 주문번호 in (select 주문번호 from 주문세부 where 제품번호 = ?)";
        return jdbcTemplate.query(query, orderRowMapper, productNumber, customerId);

    }


    @Override
    public int saveOrders(Order order) {
        String query = "insert into 주문(주문번호, 고객번호, 사원번호, 주문일, 요청일) " +
                "values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query,
                order.getOrderID(),
                order.getCustomerID(),
                order.getEmployeeID(),
                order.getOrderDate().toString(),
                order.getRequestDate().toString());
    }


    // 12/20 과제 4.주문번호와 발송일을 매개변수로 해당 주문의 발송일을 수정하는 API
    @Override
    public int updateOrderWithShippingDate(String id, String date) {
        String query = "update 주문 set 주문번호 = ? " +
                "where 발송일 = ?";
        return jdbcTemplate.update(query, id, date);
    }


    // 5. 도시별로 주문금액합 결과를 내림차순 정렬하여 조회하는 API
    @Override
    public List<Map<String, Double>> getTopCitiesByTotalOrderAmount(int limit) {
        String query = "select 고객.도시, sum(주문세부.주문수량*주문세부.단가) as 주문금액합 " +
                "from 주문세부 " +
                "join 주문 on 주문세부.주문번호 = 주문.주문번호 " +
                "join 고객 on 고객.고객번호 = 주문.고객번호 " +
                "group by 고객.도시 " +
                "order by 주문금액합 desc " +
                "limit ?";
        return jdbcTemplate.query(query, orderRowMapper2, limit);
    }


    // 6. 도시를 매개변수로 해당 도시의 년도별 주문건수를 조회하는 API
    @Override
    public List<Map<String, Double>> getOrderCountByYearForCity(String city) {
        String query = "select year(주문일) as 주문연도, count(*) from 주문 as 주문건수 " +
                "inner join 고객 on 고객.고객번호 = 주문.고객번호 " +
                "where 고객.도시 = ? " +
                "group by year(주문.주문일)";
        return jdbcTemplate.query(query, orderRowMapper3, city);
        }
    }



