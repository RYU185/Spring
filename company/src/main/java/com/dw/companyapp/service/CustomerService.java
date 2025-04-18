package com.dw.companyapp.service;

import com.dw.companyapp.model.Customer;
import com.dw.companyapp.repository.CustomerRepository;
import com.dw.companyapp.repository.MilegeGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MilegeGradeRepository milegeGradeRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll().stream().toList();
    }

    // 과제 4-1 전체 평균마일리지보다 큰 마일리지를 가진 고객들을 조회하는 API
    public List<Customer> getCustomersWithHighMileThanAvg() {
        return customerRepository.findByMileage();
    }

    // 과제 4-2 마일리지등급을 매개변수로 해당 마일리지등급을 가진 고객들을 조회하는 API
    public List<Customer> getCustomersByMileageGrade(String grade) {
        return customerRepository.findByGradeName(grade);
    }
}
