package com.dw.firstapp.Service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String hello(){
        return "Hello World from Service.";
    }

}
