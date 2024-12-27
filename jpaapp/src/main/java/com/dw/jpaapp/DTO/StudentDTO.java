package com.dw.jpaapp.DTO;

import com.dw.jpaapp.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private List<Long> courseId;
}
