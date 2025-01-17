package com.dw.dynamic.model;

import com.dw.dynamic.DTO.CourseDTO;
import com.dw.dynamic.DTO.ProductDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "payroll_subscription")
public class PayrollSubscription extends Product {
    //  추가 시 , product를 하나 만들고 추가해야함!!

    @Column(name = "title",nullable = false)
    private String title; // 구독권 제목(ex. 3개월 구독권)

    @Column(name = "start_date", updatable = false)
    private LocalDate startDate; // 구독 시작일

    @Column(name="expire_date", updatable = false)
    private LocalDate expireDate; // 구독 만료일
}
