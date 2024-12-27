package com.dw.jpaapp.model;

import com.dw.jpaapp.DTO.CourseDTO;
import com.dw.jpaapp.DTO.InstructorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "career")
    private String career;

    // 외래키로 등록하기위한 어노테이션
    // 강사 입장에서는 "1 대 다" 관계 : Instructor - Course
    @OneToMany(mappedBy = "instructor_fk")
    private List<Course> courseList = new ArrayList<>();
    // mappedBy : 둘과의 연결관계의 주인을 누구로 정할거니?
    // = instructor를 필드로 가지고 있는 Course를 주인으로 하겠다.
    // 즉 mappedBy 에는 관계 간 주체의 필드를 써야한다

    public InstructorDTO toInstructor(){
        List<Long> courseIds = courseList.stream().map(Course::getId).toList();
        return new InstructorDTO(this.id,this.name,this.career, courseIds);
    }
}
