package com.dw.jpaapp.service;

import com.dw.jpaapp.DTO.CourseDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.model.Student;
import com.dw.jpaapp.repository.CourseRepository;
import com.dw.jpaapp.repository.InstructorRepository;
import com.dw.jpaapp.repository.StudentRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    InstructorRepository instructorRepository;


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

    // 12/30 1. 검색어를 매개변수로 전달하고 검색어로 title을 가진 과목을 조회
    public List<CourseDTO> getCoursesLike(String title){
        return courseRepository.findByTitleLike("%"+title+"%").stream().map(Course::toDTO).toList();
    }

    // 2. 12/30 과목 정보를 새로 저장하는 API
    public CourseDTO saveCourse(CourseDTO courseDTO){
        Course course = new Course(); // save 1단계: 새로운 객체 생성
        course.setTitle(courseDTO.getTitle()); // 2단계: id는 update 아니면 넣지말기,
                                            // 객체 안에 들어갈 정보들을 DTO에서 꺼내서 넣기
        course.setDescription(courseDTO.getDescription());
        course.setInstructor_fk(                // 2-1 강사 정보를 불러와야하므로 강사 레포지토리를 가져오고 예외처리
                instructorRepository
                .findById(courseDTO.getInstructorId()) //
                .orElseThrow(()->new RuntimeException("No Instructor")));
        List<Student> studentList = new ArrayList<>();
        for (Long id : courseDTO.getStudentId()){
            studentList.add(studentRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("no student")));
        }
        course.setStudentList(studentList);
        return courseRepository.save(course).toDTO();
    }
}
