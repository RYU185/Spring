package com.dw.jdbcapp.DTO;

import java.time.LocalDate;

public class EmployeeDepartmentDTO {
    private LocalDate joinDate;
    private String departmentName;
    private String employeeName;

    public EmployeeDepartmentDTO() {
    }

    public EmployeeDepartmentDTO(LocalDate joinDate, String departmentName, String employeeName) {
        this.joinDate = joinDate;
        this.departmentName = departmentName;
        this.employeeName = employeeName;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return this.joinDate+ ", "+ this.departmentName+", "+this.employeeName;
    }
}
