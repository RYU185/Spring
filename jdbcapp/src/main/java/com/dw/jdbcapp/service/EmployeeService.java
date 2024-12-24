package com.dw.jdbcapp.service;

import com.dw.jdbcapp.DTO.EmployeeDepartmentDTO;
import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.repository.iface.EmployeeRepository;
import com.dw.jdbcapp.repository.jdbc.EmployeeJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class EmployeeService {
    @Autowired
    @Qualifier("employeeTemplateRepository")
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(String id){
        return employeeRepository.getEmployeeById(id);
    }

    public List<Map<String, Object>> getEmployeesWithDepartName(){
        return employeeRepository.getEmployeesWithDepartName();
    }

    public List<EmployeeDepartmentDTO> getEmployeesWithDepartName2(){
        List<EmployeeDepartmentDTO> employeeDepartmentDTOList =
                new ArrayList<>();

        List<Map<String,Object>> mapList =
                employeeRepository.getEmployeesWithDepartName();

        for(Map<String,Object> data : mapList ) {
            EmployeeDepartmentDTO temp = new EmployeeDepartmentDTO(
                    LocalDate.parse((String)data.get("입사일")),
                    (String) data.get("부서명"),
                    (String) data.get("이름")

            );
            System.out.println(temp);
            employeeDepartmentDTOList.add(temp);
        }
        return employeeDepartmentDTOList;
    }

    public List<Employee> getEmployeeWithDepartPosition (String departmentNumber, String employeePosition) {
        List<Employee> employees = employeeRepository.getEmployeeWithDepartPosition(departmentNumber, employeePosition);
        if (employees.isEmpty()){
            throw new ResourceNotFoundException("존재하지 않는 사원정보입니다: "+departmentNumber+":"+employeePosition);
        }
            return employees;
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.saveEmployee(employee);
    }

    // 과제 3-1  입사일을 매개변수로 해당 입사일 이후로 입사한 사원들을 조회하는 API
    // 과제 3-2. hiredate를 0으로 입력하면 가장 최근 입사한 사원의 정보를 조회하시오.
    public List<Employee> getEmployeesByHireDate(String hireDate) {

        try {
            if (hireDate.equals("0")) {
                return employeeRepository.getRecentEmployee();
            } else {
                return employeeRepository.getEmployeesByHireDate(LocalDate.parse(hireDate));
            }
        }catch (DateTimeParseException e){
            throw new InvalidRequestException("올바른 사원정보가 아닙니다");
        }
    }
}
