package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Employee;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(String id);

    List<Map<String, Object>> getEmployeesWithDepartName();

    List<Employee> getEmployeeWithDepartPosition(String departmentNumber, String employeePosition);

    Employee saveEmployee(Employee employee);

    // 과제 3-1  입사일을 매개변수로 해당 입사일 이후로 입사한 사원들을 조회하는 API
   List <Employee> getEmployeesByHireDate(LocalDate hireDate);

   List<Employee> getRecentEmployee();
}
