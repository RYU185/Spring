package com.dw.jdbcapp.service;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.repository.iface.CustomerRepository;
import com.dw.jdbcapp.repository.jdbc.CustomerJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    @Qualifier("customerTemplateRepository")
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.getAllCustomers();
    }

    // 12.20 과제. 평균마일리지보다 큰 마일리지를 가진 고객들을 조회하는 API
    public List<Customer> getCustomersWithHighMileThanAvg(){
        return customerRepository.getCustomersWithHighMileThanAvg();
    }

    public List<Customer> getCustomersByMileageGrade(String gradeName){
        return customerRepository.getCustomersByMileageGrade(gradeName);
    }
}
