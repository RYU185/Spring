package com.dw.jpaapp.DTO;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CourseDTO {
    // 양방향이기 때문에 객체를 쓰지않는다
    // 양방향을 포기하지 않고 쓴다면 DTO를 사용
    // 객체를 주지말고 id, title, description 등을 바로 주자
    private Long id;
    private String title;
    private String description;
    private Long instructorId;
    private List<Long> studentId;
}
