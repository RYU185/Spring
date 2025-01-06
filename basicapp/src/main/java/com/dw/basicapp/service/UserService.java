package com.dw.basicapp.service;

import com.dw.basicapp.model.User;
import com.dw.basicapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional // 위험한 상황을 대비해 롤백
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User postUser(User user){
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
