package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.Department;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getAllCustomers();

    // 12.20 과제.  평균마일리지보다 큰 마일리지를 가진 고객들을 조회하는 API
    List<Customer> getCustomersWithHighMileThanAvg();

    List<Customer> getCustomersByMileageGrade(String gradeName);
}



