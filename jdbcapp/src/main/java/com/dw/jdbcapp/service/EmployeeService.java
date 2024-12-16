package com.dw.jdbcapp.service;

import com.dw.jdbcapp.DTO.EmployeeDepartmentDTO;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class EmployeeService {
    @Autowired
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

    public List<Employee> getEmployeeWithDepartPosition (String departmentNumber, String employeePosition){
        return employeeRepository.getEmployeeWithDepartPosition(departmentNumber,employeePosition);
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.saveEmployee(employee);
    }
}
