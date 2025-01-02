package com.dw.companyapp.repository;

import com.dw.companyapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("select c from Customer c where c.mileage > (select avg(c.mileage) from Customer c")
    Optional<Customer> findByMileage();

    @Query("select c from Customer join MileageGrade m on c.grade > m.lowerMileage and c.grade < m.upperMileage " +
            "gradeName = :gradeName")
    Optional<Customer> findByGradeName(String grade);
}
