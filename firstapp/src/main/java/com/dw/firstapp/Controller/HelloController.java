package com.dw.firstapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // 이 어노테이션이 아래 클래스에서 /sayhello를 입력했을 때 메서드를 수행하게 해줌
                 // 주소창에 써져있는 모든 주소값을 처리해주는 것이 현재 클래스인 controller 컨트롤러
                 // 반드시 하나의 주소값에는 하나의 메서드

public class HelloController {
    @GetMapping("/sayhello")  // 대부분 문자열이 들어가고 슬래쉬(/) 시작 후 소문자
        public String hello() {
            return "Hello World!";
    }
}
