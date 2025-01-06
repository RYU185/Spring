package com.dw.companyapp.repository;

import com.dw.companyapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    // 과제 4-1 전체 평균마일리지보다 큰 마일리지를 가진 고객들을 조회하는 API
    @Query("select c from Customer c where c.mileage > (select avg(c2.mileage) from Customer c2)")
    List<Customer> findByMileage();

    @Query("select c from Customer c join MileageGrade m " +
            "on c.mileage < m.upperMileage and c.mileage > m.lowerMileage " +
            "where m.gradeName = :grade")
    List<Customer> findByGradeName(String grade);

}
