package com.dw.jpaapp.repository;

import com.dw.jpaapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> { // < 클래스, PK의 데이터타입 >
    Optional<Course> findByTitleLike(String title);
}
