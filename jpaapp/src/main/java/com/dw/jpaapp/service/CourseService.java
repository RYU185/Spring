package com.dw.jpaapp.service;

import com.dw.jpaapp.DTO.CourseDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<CourseDTO> getAllCourses(){
//        List<CourseDTO> courseDTOS = new ArrayList<>();
//        for(Course data : courseRepository.findAll()){
//            courseDTOS.add(data.toDTO());
//        }
//        return courseDTOS;
        return courseRepository.findAll().stream().map(Course::toDTO).collect(Collectors.toList());
        // stream(): 일렬로 세워서 전송
        // map(): 변형시키다 (위 코드에서는 CourseDTO로 변형시키는 과정)
        // filter(): 참/거짓을 리턴하는 조건식 필요. 참이면 보내고 거짓이면 버림

    }
}
