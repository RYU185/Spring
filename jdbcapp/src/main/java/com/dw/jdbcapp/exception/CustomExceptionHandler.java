package com.dw.jdbcapp.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 이 ExceptionHandler의 역할은 개발자가 만든 사용자정의 예외를 모두 직접 응답처리하는 것
// 이전 InvaildRequestException으로 던졌던 Service의 문구
// "존재하지 않는 제품번호입니다: "를 내가 정의하기 위해.
// 응답 메시지의 형태와 내용을 모두 결정할 수 있어서 편리함
// 백엔드와 프론트엔드 개발자 사이에 협의된 형태로 전달하는 것이 일반적임!!!



@Order(Ordered.HIGHEST_PRECEDENCE) // 중복된 예외처리의 우선순위
                                   // 이 클래스에서 언급한 것은 모두 우선순위로
@RestControllerAdvice // 컨트롤러, 서비스, 레포지토리의 예외처리를 이 클래스가 맡아서 하겠다라는 어노테이션
public class CustomExceptionHandler {
    @ExceptionHandler(InvalidRequestException.class)
    // ()소괄호 안에 선언한 예외클래스를 핸들링하는 메서드
    // 자바는 클래스 이름만 필요한 경우에 반드시 클래스명.class라고 명시해야함
    public ResponseEntity<String> handleInvalidRequestException(
            InvalidRequestException e){
        return new ResponseEntity<>(
                e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
