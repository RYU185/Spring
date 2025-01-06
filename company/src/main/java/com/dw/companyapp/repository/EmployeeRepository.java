package com.dw.companyapp.repository;

import com.dw.companyapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query("select e from Employee e  ")
    List<Map<String, Object>> findByDepartName();
}
