package com.dw.jpaapp.model;

import com.dw.jpaapp.DTO.InstructorDTO;
import com.dw.jpaapp.DTO.InstructorProfileDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "instructor_profile")
public class InstructorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bio", length = 3000 ) // length는 글자수 (바이트 X)
    private String bio; // 강사 소개

    @Column(name = "github_url")
    private String githubUrl;

    @OneToOne
    @JoinColumn(name = "instructor_id") // 단방향 참조
    private Instructor instructor;

    // Instructor 매핑 메서드
    public InstructorProfileDTO toDTO(){
        return new InstructorProfileDTO(
                this.id,
                this.bio,
                this.githubUrl,
                this.instructor.getId());
    }
}
