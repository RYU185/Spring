package com.dw.jpaapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor  // 매개변수 없는 기본생성자
@AllArgsConstructor // 매개변수 있는 생성자
@Getter // get
@Setter // set
@Entity // 이 클래스가 테이블과 1대1로 매핑된다는 것을 알리는 어노테이션
@Table(name = "student") // 명시적으로 테이블이름 설정
public class Student {
    @Id // Primary Key 로 설정 ( @Column 이 @Id에 포함되기때문에 잘 쓰지않는다. )
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 아이디 숫자 자동증가
    private Long id; // Long: 자동으로 1부터 하나씩 증가한다는 뜻
                     // int가 아니고 Long을 쓰는 이유: int도 범위가 넓긴 하지만 한번 쓴 숫자는 삭제를 해도 재사용하지않고 증가만 하기 때문.

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true) // nullable : 값을 절대로 입력해야함 , unique : 중복 불가
    private String email;

    // "다 대 다" 관계 : Student - Course 관계
    // 무조건 테이블을 하나 만들어야 함 - 외래키가 없다
    @ManyToMany(mappedBy = "studentList")
    private List<Course> coursesList = new ArrayList<>();

}
