package com.dw.jdbcapp.model;

public class Department {
    String departmentName;
    String departmentNumber;

    public Department() {
    }

    public Department(String departmentName, String departmentNumber) {
        this.departmentName = departmentName;
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", departmentNumber='" + departmentNumber + '\'' +
                '}';
    }
}
