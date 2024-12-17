package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.repository.iface.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String query = "select * from 사원 where 사원번호";
        return jdbcTemplate.queryForObject(query,employeeRowMapper,id);
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
            employee.put("입사일",rs.getString("입사일"));
            employee.put("부서명", rs.getString("부서명"));
            return employee;
        });
    }

    @Override
    public List<Employee> getEmployeeWithDepartPosition(String departmentNumber, String employeePosition) {
        String query = "select 이름, 입사일, 부서명 from 사원 "
                + "inner join 부서 on 사원.부서번호 = 부서.부서번호";
        return jdbcTemplate.query(query, employeeRowMapper, departmentNumber, employeePosition);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        String query = "insert into 사원(사원번호, 이름, 영문이름, 직위, 성별, 생일, 입사일, 주소, 도시, 지역, 집전화, 상사번호, 부서번호) "
                +"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
}
