package com.dw.secondapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Secondhihihi {
    @GetMapping("/ryu185")
    public String hi501(){
        return "안녕하세요";
    }
}
