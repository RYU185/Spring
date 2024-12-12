package com.dw.secondapp.service;

import com.dw.secondapp.repository.SecondHelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class SecondHelloService {
    @Autowired
    SecondHelloRepository secondHelloRepository;

    @GetMapping
    public String srHello(){
        return secondHelloRepository.hello();
    }
}
