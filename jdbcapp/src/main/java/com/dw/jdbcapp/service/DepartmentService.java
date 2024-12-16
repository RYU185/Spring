package com.dw.jdbcapp.service;

import com.dw.jdbcapp.model.Department;
import com.dw.jdbcapp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(){
        return departmentRepository.getAllDepartments();
    }

    public Department saveDepartment(Department department){
        return departmentRepository.saveDepartment(department);
    }

    public List<Department> saveDepartmentlist(List<Department> departmentList){
        for (Department data: departmentList){
            departmentRepository.saveDepartment(data);
        }
        return departmentList;
    }

    public Department updateDepartment(Department department){
        return departmentRepository.updateDepartment(department);
    }

}
