package com.dw.jpaapp.model;

import com.dw.jpaapp.DTO.CourseDTO;
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
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    // 외래키로 등록하기 위한 어노테이션
    // 과목입장에서는 "다 대 1" 관계
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor_fk;
    // Course가 Instructor 와의 관계에서 단일값을 갖기 때문에
    // Instructor의 관계 상 주체 노릇을 할 수 있다.

    // 순환참조 문제 발생
    // JSON이 String으로 일일히 바꾸는 과정이기 때문에 객체가 본인객체를 참조하는 상황 = 순환참조가 일어난다
    // 이를 해결하기 위해서는 DTO를 활용해야함
    //

    // "다 대 다" 관계 : Course - Student 관계
    // "다 대 다" 관계에서는 Column을 Join할 수 없음 ( 데이터 정규화에 따라 하나의 컬럼에는 여러개의 데이터를 가질 수 없기때문 )
    @ManyToMany
    @JoinTable(name = "course_student", // 테이블을 하나 만들고 그 이름은 course_student
            joinColumns = @JoinColumn(name = "course_id"), // 컬럼 하나 만들고 이름은 course_id
            inverseJoinColumns = @JoinColumn(name = "student_id")) // 반대쪽 컬럼을 하나 만들고 이름은 student_id
    private List<Student> studentList = new ArrayList<>();
    // 무조건 테이블을 하나 만들어야 함
    // course_student 의 역할은 "1 대 다"의 관계로 쪼개버린 것
    // ("1 대 다" 관계에서 외래키는 "다" 쪽이 가진다. 즉 course_student는 외래키를 동시에 두개 가지고 있는 것.)
    // course_student Entity 가 외래키를 가져버렸으므로 course가 노예(1), course_student가 주인(다)이 됨
    // DBeaver에서 레퍼런스를 보면 Owner가 course_student로 되어있음

    // CourseDTO 매핑 메서드
    // DTO에 만들어도 되고 Entity에 만들어도 됨
    public CourseDTO toDTO(){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(this.id);
        courseDTO.setTitle(this.title);
        courseDTO.setDescription(this.description);
        courseDTO.setInstructorId(this.instructor_fk.getId());
        List<Long> studentIds = new ArrayList<>();
        for (Student data: studentList) {
            studentIds.add(data.getId());
        }
        courseDTO.setStudentId(studentIds);
        return courseDTO;
    }
}
