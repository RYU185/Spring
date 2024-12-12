package com.dw.jdbcapp.model;

import java.time.LocalDate;

public class Employee {
    String employeeNumber;
    String employeeID;
    String englishName;
    String employeePosition;
    String gender;
    LocalDate birthday;
    LocalDate joinDate;
    String employeeAddress;
    String city;
    String region;
    String homeCall;
    String superiorNumber;
    String departmentNumber;

    public Employee() {
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHomeCall() {
        return homeCall;
    }

    public void setHomeCall(String homeCall) {
        this.homeCall = homeCall;
    }

    public String getSuperiorNumber() {
        return superiorNumber;
    }

    public void setSuperiorNumber(String superiorNumber) {
        this.superiorNumber = superiorNumber;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", employeeID='" + employeeID + '\'' +
                ", englishName='" + englishName + '\'' +
                ", employeePosition='" + employeePosition + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", joinDate='" + joinDate + '\'' +
                ", employeeAddress='" + employeeAddress + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", homeCall='" + homeCall + '\'' +
                ", superiorNumber='" + superiorNumber + '\'' +
                ", departmentNumber='" + departmentNumber + '\'' +
                '}';
    }
}
