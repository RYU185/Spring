package com.dw.jpaapp.controller;

import com.dw.jpaapp.DTO.InstructorDTO;
import com.dw.jpaapp.model.Instructor;
import com.dw.jpaapp.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InstructorController {
    @Autowired
    InstructorService instructorService;

    @GetMapping("/instructors")
    public ResponseEntity<List<InstructorDTO>> getAllInstructors(){
        return new ResponseEntity<>(
                instructorService.getAllInstructors(),
                HttpStatus.OK
        );
    }

    // 12/30 3. Instructor의 id를 매개변수로 강사의 정보를 조회
    @GetMapping("/instructor/{id}")
    public ResponseEntity <InstructorDTO> getInstructor(Long id){
        return new ResponseEntity<>(
                instructorService.getInstructor(id),
                HttpStatus.OK
        );
    }
}
