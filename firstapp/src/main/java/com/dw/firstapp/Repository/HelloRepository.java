package com.dw.firstapp.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {
    @Autowired
    HelloRepository helloRepository;

    public String hello(){
        return helloRepository.hello();
    }
}
