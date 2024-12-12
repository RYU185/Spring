package com.dw.jdbcapp.model;

public class Customer {
    String customerNumber;
    String customerCompany;
    String managerName;
    String managerPosition;
    String address;
    String city;
    String region;
    String phone;
    int mileage;

    public Customer() {
    }

    public Customer(String customerNumber, String customerCompany, String managerName, String managerPosition, String address, String city, String region, String phone, int mileage) {
        this.customerNumber = customerNumber;
        this.customerCompany = customerCompany;
        this.managerName = managerName;
        this.managerPosition = managerPosition;
        this.address = address;
        this.city = city;
        this.region = region;
        this.phone = phone;
        this.mileage = mileage;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPosition() {
        return managerPosition;
    }

    public void setManagerPosition(String managerPosition) {
        this.managerPosition = managerPosition;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerNumber='" + customerNumber + '\'' +
                ", customerCompany='" + customerCompany + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerPosition='" + managerPosition + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", phone='" + phone + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}