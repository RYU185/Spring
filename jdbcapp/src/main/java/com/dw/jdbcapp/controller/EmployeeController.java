package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.DTO.EmployeeDepartmentDTO;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/find-all-employee")
    public ResponseEntity<List<Employee>> getAllEmployee(){
       return new ResponseEntity<>(employeeService.getAllEmployees(),
               HttpStatus.OK);
    }

    // Query Parameters 쿼리 문자열 처리
    @GetMapping("/employee")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam String id){
        return new ResponseEntity<>(employeeService.getEmployeeById(id),
                HttpStatus.ACCEPTED);
    }

    // Pass Parameter 경로 매개변수
    @GetMapping("/employee/{id}")
    public ResponseEntity <Employee> getEmployeeById_2(@PathVariable String id){
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    // 새로운 클래스에 담아서 내보내기
    @GetMapping("/employees/department")
    public ResponseEntity <List<Map<String,Object>>> getEmployeesWithDepartName(){
        return new ResponseEntity<>(employeeService.getEmployeesWithDepartName(),
                HttpStatus.OK);
    }
    @GetMapping("/employees/department2")
    public ResponseEntity <List<EmployeeDepartmentDTO>> getEmployeeWithDepartment2(){
        return new ResponseEntity<>(
                employeeService.getEmployeesWithDepartName2(),
                HttpStatus.OK);
    }

    // 12/13 과제
    @GetMapping("/employees/{departmentNumber}/{employeePosition}")
    public ResponseEntity<List<Employee>> getEmployeeWithDepartPosition(@PathVariable String departmentNumber, @PathVariable String employeePosition) {
        return new ResponseEntity<>(
                employeeService.getEmployeeWithDepartPosition(departmentNumber, employeePosition),
                HttpStatus.OK);
    }
    
    @GetMapping("/employee?departmentNumber&employeePosition")
    public ResponseEntity<List <Employee>> getEmployeeWithDepartPosition_2(@RequestParam String departmentNumber, @RequestParam String employeePosition){
        return new ResponseEntity<>(
                employeeService.getEmployeeWithDepartPosition(departmentNumber,employeePosition),
                HttpStatus.OK);
    }

    // #3.
    @PostMapping("/post/employee")
    public ResponseEntity <Employee> saveEmployee (@RequestBody Employee employee){
        return new ResponseEntity<>(
                employeeService.saveEmployee(employee),
                HttpStatus.CREATED);

    }

    // 12/20 과제 3-1 입사일을 매개변수로 해당 입사일 이후로 입사한 사원들을 조회하는 API
    @GetMapping("/employee/joinDate/{joinDate}")
    public ResponseEntity<List<Employee>> getEmployeesByHireDate(@PathVariable String hireDate){
        return new ResponseEntity<>(
                employeeService.getEmployeesByHireDate(hireDate),
                HttpStatus.OK
        );
    }
}
