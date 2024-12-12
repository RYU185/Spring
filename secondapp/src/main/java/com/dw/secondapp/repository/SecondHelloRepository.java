package com.dw.secondapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public class SecondHelloRepository {
    @GetMapping("/hello")
    public String hello(){
        return "say hello from Repository";
    }
}
