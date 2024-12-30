package com.dw.jpaapp.DTO;

import com.dw.jpaapp.model.InstructorProfile;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InstructorProfileDTO {
    private Long id;
    private String bio;
    private String githubUrl;
    private Long instructorId;
}
