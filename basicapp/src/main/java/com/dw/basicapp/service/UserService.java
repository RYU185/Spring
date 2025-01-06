package com.dw.basicapp.service;

import com.dw.basicapp.model.User;
import com.dw.basicapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional // 위험한 상황을 대비해 롤백
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User postUser(User user){
        User user1 = new User();
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        user1.setRealName(user.getRealName());
        user1.setRole(user.getRole());
        user1.setCreatedAt(user.getCreatedAt());
        return userRepository.save(user1);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
