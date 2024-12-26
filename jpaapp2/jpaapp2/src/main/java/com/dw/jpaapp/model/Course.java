package com.dw.jpaapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor_fk;

    // student와의 관계 - 다 대 다
    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"), // 주인
            inverseJoinColumns = @JoinColumn(name = "student_id")) // 노예
    private List<Student> studentList = new ArrayList<>();
}
