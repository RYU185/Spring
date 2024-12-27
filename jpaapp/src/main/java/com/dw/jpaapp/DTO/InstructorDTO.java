package com.dw.jpaapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstructorDTO {
    private Long id;
    private String name;
    private String career;
    private List<Long> courseId;
}
