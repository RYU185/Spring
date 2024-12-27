package com.dw.jpaapp.service;


import com.dw.jpaapp.DTO.StudentDTO;
import com.dw.jpaapp.model.Student;
import com.dw.jpaapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents(){
        return studentRepository.findAll().stream().map(Student::toStudentDTO).collect(Collectors.toList());
    }
}
