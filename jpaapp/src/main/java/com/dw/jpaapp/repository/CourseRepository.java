package com.dw.jpaapp.repository;

import com.dw.jpaapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> { // < 클래스, PK의 데이터타입 >
    // 12/30 과제 1. 검색어를 매개변수로 전달하고 검색어로 title을 가진 과목을 조회
    List<Course> findByTitleLike(String title);
}
