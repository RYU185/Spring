package com.dw.dynamic.controller;

import com.dw.dynamic.DTO.CourseDTO;
import com.dw.dynamic.model.Course;
import com.dw.dynamic.repository.CourseRepository;
import com.dw.dynamic.service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses(){
        return new ResponseEntity<>(
                courseService.getAllCourses(),
                HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Course>> getCoursesByTitle(String title){
        return new ResponseEntity<>(
                courseService.getCoursesByTitle(title),
                HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity<CourseDTO> saveCourse(CourseDTO courseDTO){
        return new ResponseEntity<>(
                courseService.saveCourse(courseDTO),
                HttpStatus.CREATED
        );
    }
}
