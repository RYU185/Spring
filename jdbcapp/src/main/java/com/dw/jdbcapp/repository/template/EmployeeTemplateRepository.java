package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.repository.iface.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeTemplateRepository implements EmployeeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Employee> employeeRowMapper = new RowMapper<Employee>() {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
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
            return employee;
        }
    };

    @Override
    public List<Employee> getAllEmployees() {
        String query = "select * from 사원";
        return jdbcTemplate.query(query,employeeRowMapper);
    }

    @Override
    public Employee getEmployeeById(String id) {
        String query = "select * from 사원 where 사원번호 = ?";
        try {
            return jdbcTemplate.queryForObject(query, employeeRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new InvalidRequestException("사원번호가 올바르지 않습니다: " + id);
        }
    }

    @Override
    public List<Map<String, Object>> getEmployeesWithDepartName() {
        String query = "select 이름, 입사일, 부서명 from 사원 " +
                "inner join 부서 on 사원.부서번호 = 부서.부서번호";
//        RowMapper<Map<String,Object>>mapper =
//                new RowMapper<Map<String, Object>>() {
//                    @Override
//                    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        Map<String, Object> employee = new HashMap<>();
//                        employee.put("이름", rs.getString("이름"));
//                        employee.put("입사일",rs.getString("입사일"));
//                        employee.put("부서명", rs.getString("부서명"));
//                        return employee;
//                    }
//                };
//                return jdbcTemplate.query(query,mapper);
        // 이게 요즘 스타일!
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Map<String, Object> employee = new HashMap<>();
            employee.put("이름", rs.getString("이름"));
            employee.put("입사일", rs.getString("입사일"));
            employee.put("부서명", rs.getString("부서명"));
            return employee;
        });

  }


    @Override
    public List<Employee> getEmployeeWithDepartPosition(String departmentNumber, String employeePosition) {
        String query = "select 사원.이름, 사원.입사일, 부서.부서명 from 사원 "
                + "inner join 부서 on 사원.부서번호 = 부서.부서번호 " +
                "where 사원.부서번호 = ? and 사원.직위 = ?";
            return jdbcTemplate.query(query, employeeRowMapper, departmentNumber, employeePosition);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        String query = "insert into 사원(사원번호, 이름, 영문이름, 직위, 성별, 생일, 입사일, 주소, 도시, 지역, 집전화, 상사번호, 부서번호) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            jdbcTemplate.update(query,
                    employee.getEmployeeNumber(),
                    employee.getEmployeeID(),
                    employee.getEnglishName(),
                    employee.getEmployeePosition(),
                    employee.getGender(),
                    employee.getJoinDate(),
                    employee.getEmployeeAddress(),
                    employee.getCity(),
                    employee.getRegion(),
                    employee.getHomeCall(),
                    employee.getSuperiorNumber(),
                    employee.getDepartmentNumber()
            );
            return employee;
        }


    // 과제 3-1. 입사일을 매개변수로 해당 입사일 이후로 입사한 사원들을 조회하는 API
    // 과제 3-2. hiredate를 0으로 입력하면 가장 최근 입사한 사원의 정보를 조회하시오.
    @Override
    public List<Employee> getEmployeesByHireDate(LocalDate hireDate) {
        String query = "select * from 사원 " +
                        "where 사원.입사일 > ?";
            return jdbcTemplate.query(query, employeeRowMapper, hireDate);
    }

    // 3-3  hiredate가 0이 아니고 LocalDate형태도 아니면
    // InvalidRequestException 예외처리 하시오
    @Override
    public List<Employee> getRecentEmployee(){
            String query = "select * from 사원 " +
                    "where 입사일 = (select 입사일 from 사원 order by 입사일 desc limit 1)";
            return jdbcTemplate.query(query, employeeRowMapper);
    }
}

