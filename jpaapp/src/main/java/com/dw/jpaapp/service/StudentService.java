package com.dw.jpaapp.service;


import com.dw.jpaapp.DTO.CourseDTO;
import com.dw.jpaapp.DTO.StudentDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.model.Student;
import com.dw.jpaapp.repository.CourseRepository;
import com.dw.jpaapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(Student::toStudentDTO).collect(Collectors.toList());
    }

    // JPA 메서드 쿼리를 연습하기 위한 예제
    // 여러 방식의 메서드 쿼리를 수행해보는 연습 메서드
    public String getStudentInfo() {
        // findByName : 이름 필드(name) 기준으로 학생 데이터를 조회하는 메서드 쿼리
        // return studentRepository.findByName("Steve").toStudentDTO().toString();
        // findByName 이 List<Student>를 리턴하는 경우
        // return studentRepository.findByName("Steve").stream().map(Student::toStudentDTO).toList().toString();

        // findByName 이 Optional<Student>를 리턴하는 경우
        // Optional은 내부에 null 데이터를 안전하게 가질 수 있음
//        Optional<Student> student = studentRepository.findByName("Steve");
//        if (student.isEmpty()){
//            throw new RuntimeException("없는 데이터");
//        }
//        return student.get().toStudentDTO().toString();
//        // get()? : 안에 있는 데이터를 리턴 (null 이 아닌경우 리턴)

        // 위 코드의 람다식
        return studentRepository.findByName2("Steve")
                .map(Student::toStudentDTO)
                .map(StudentDTO::toString)
                .orElseThrow(()->new RuntimeException("없는 데이터"));
    }

    public StudentDTO saveStudent(StudentDTO studentDTO){
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        List<Course> courseList = new ArrayList<>();
        for (Long id : studentDTO.getCourseId()) {
            Optional<Course> courseOptional = courseRepository.findById(id);
            if (courseOptional.isPresent()){
                Course course = courseOptional.get();
                course.getStudentList().add(student);
                courseList.add(course);
            }
        }
        student.setCoursesList(courseList);
        return studentRepository.save(student).toStudentDTO();
    }
}
