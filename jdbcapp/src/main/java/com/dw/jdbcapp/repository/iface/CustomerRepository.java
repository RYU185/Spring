package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.Department;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getAllCustomers();

    // 12.20 과제.1  평균마일리지보다 큰 마일리지를 가진 고객들을 조회하는 API
    List<Customer> getCustomersWithHighMileThanAvg();

    //12.20 과제2  마일리지등급을 매개변수로 해당 마일리지등급을 가진 고객들을 조회하는 API
    List<Customer> getCustomersByMileageGrade(String gradeName);
}



