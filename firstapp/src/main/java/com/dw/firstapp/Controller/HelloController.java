package com.dw.firstapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/sayhello")  // 대부분 문자열이 들어가고 슬래쉬(/) 시작 후 소문자
        public String hello() {
            return "Hello World!";
    }
}
