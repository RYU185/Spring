package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.DTO.EmployeeDepartmentDTO;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/find-all-employee")
    public List<Employee> getAllEmployee(){
       return employeeService.getAllEmployees();
    }

    // Query Parameters 쿼리 문자열 처리
    @GetMapping("/employee")
    public Employee getEmployeeById(@RequestParam String id){
        return employeeService.getEmployeeById(id);
    }

    // Pass Parameter 경로 매개변수
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById_2(@PathVariable String id){
        return employeeService.getEmployeeById(id);
    }

    // 새로운 클래스에 담아서 내보내기
    @GetMapping("/employees/department")
    public List<Map<String,Object>> getEmployeesWithDepartName(){
        return employeeService.getEmployeesWithDepartName();
    }
    @GetMapping("/employees/department2")
    public List<EmployeeDepartmentDTO> getEmployeeWithDepartment2(){
        return employeeService.getEmployeesWithDepartName2();
    }

    // 12/13 과제
    @GetMapping("/employees/{departmentNumber}/{employeePosition}")
    public List <Employee> getEmployeeWithDepartPosition(@PathVariable String departmentNumber, @PathVariable String employeePosition){
        return employeeService.getEmployeeWithDepartPosition(departmentNumber,employeePosition);
    }
    
    @GetMapping("/employee?departmentNumber&employeePosition")
    public List <Employee> getEmployeeWithDepartPosition_2(@RequestParam String departmentNumber, @RequestParam String employeePosition){
        return employeeService.getEmployeeWithDepartPosition(departmentNumber,employeePosition);
    }

    // #3.
    @PostMapping("/post/employee")
    public Employee saveEmployee (@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }
}
