package com.dw.secondapp.controller;

import com.dw.secondapp.service.SecondHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Secondcontroller {
    @Autowired
    SecondHelloService secondHelloService;

    @GetMapping("/ryu185")
    public String hi501(){
        return secondHelloService.srHello();
    }
}
