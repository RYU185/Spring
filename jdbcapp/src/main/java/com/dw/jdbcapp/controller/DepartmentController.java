package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Department;
import com.dw.jdbcapp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/find-all-department")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return new ResponseEntity<>(
                departmentService.getAllDepartments(),
                HttpStatus.CREATED
                );
    }

    // 12/16
    // single data (저장할 데이터 객체 1개 처리용)
    @PostMapping("/post/department")
    public ResponseEntity <Department> saveDepartment(@RequestBody Department department) { // 메세지의 바디에서 꺼낸다
        return new ResponseEntity<>(
                departmentService.saveDepartment(department),
                HttpStatus.CREATED);
    }

    // multiple data (저장할 데이터가 리스트인 경우)
    @PostMapping("/post/departmentlist")
    public ResponseEntity<List<Department>> saveDepartmentlist(@RequestBody List<Department> departmentList) { // 메세지의 바디에서 꺼낸다
        return new ResponseEntity<>(
                departmentService.saveDepartmentlist(departmentList),
                HttpStatus.CREATED);
    }

    @PutMapping("/put/department")
    public ResponseEntity <Department> updateDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.updateDepartment(department),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/department/id/{id}")
    public String deleteDepartment(@PathVariable String id) {
        return "부서번호 : " + departmentService.deleteDepartment(id) + " 가 삭제되었습니다";
    }


}
