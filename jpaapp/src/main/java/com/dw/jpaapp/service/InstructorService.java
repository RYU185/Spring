package com.dw.jpaapp.service;

import com.dw.jpaapp.DTO.InstructorDTO;
import com.dw.jpaapp.model.Instructor;
import com.dw.jpaapp.repository.CourseRepository;
import com.dw.jpaapp.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    CourseRepository courseRepository;

    public List<InstructorDTO> getAllInstructors(){
        return instructorRepository.findAll().stream().map(Instructor::toInstructor).collect(Collectors.toList());
    }

    // 5-3 Instructor의 id를 매개변수로
    public InstructorDTO getInstructor(Long id){
        return instructorRepository.findById(id).map(Instructor::toInstructor).orElseThrow(()->new RuntimeException("존재하지 않는 아이디"));
    }

    // 5-4
    public InstructorDTO saveInstructor(InstructorDTO instructorDTO){
        Instructor instructor = new Instructor();
        instructor.setName(instructorDTO.getName());
        instructor.setCareer(instructorDTO.getCareer());
        instructor.setCourseList(instructorDTO.getCourseId().stream()
                .map(id->courseRepository.findById(id))
                .map(optional->optional.orElseThrow(()-> new RuntimeException("No course")))
                .toList());
        return instructorRepository.save(instructor).toInstructor();
    }
}
