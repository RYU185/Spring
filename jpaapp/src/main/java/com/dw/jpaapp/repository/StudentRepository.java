package com.dw.jpaapp.repository;

import com.dw.jpaapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // 이 메서드는 JpaRepository 에 등록되지 않은 추상메서드이므로 우리가 선언을 해줘야함
    // Student findByName(String name);
    // List<Student> findByName(String name);
    // 리턴형을 List에 담아서 응답하도록 선언할 수 있음
    // List에 담아서 내보내는 것이 더 안정적이고 좋은 방법 : 값을 리턴한다는 것은 메모리의 위치를 리턴한다는 것
    // List는 null를 직접 가르키지 않도록 되어있다. null은 프로그램 종료로 연결될 수 있으므로 위험하기 때문.
    // null을 List에 담으려하지 않으므로 그저 Empty (비어있는) 상태가 됨

    // 단일객체 (Student) 의 리턴함수일 경우, null을 리턴할 가능성이 있으므로
    // 아래 3가지 방법중에 하나를 사용하는 것이 좋음
    // 1. null 체크를 수행하는 예외처리
    // 2. null 데이터에 안전한 List 컬렉션을 사용
    // 3. Optional 객체를 사용
    // Optional 은 null을 받기는 하되, 외부에 공개하지않고 Wrapping 할 수 있음

    Optional<Student> findByName(String name);
    Student findByEmail(String email);
    Student findByNameAndEmail(String name, String email);
    Student findByNameLike(String name);
    // findBy 조합으로 뒤에 나오는 것들(Email, NameAndEmail, NameLike etc)은
    // 모두 model 에 있는 필드명이다. 데이터베이스와는 전혀 관계가 없음!!
    // 매개변수로 받아주는 애들은 그냥 매핑을 시키기 위한 것일 뿐 이름을 뭐라하던 상관없음.


    // JPQL
    // JPA 메서드 쿼리의 작명법에 어긋나는 메서드를 사용하고자 하면
    // 이 메서드가 수행해야 할 SQL 쿼리를 직접 작성해주면
    // JPA 는 수행 가능함
    @Query("select s from Student s where s.name = :name")
    Optional<Student> findByName2(String name);

}
