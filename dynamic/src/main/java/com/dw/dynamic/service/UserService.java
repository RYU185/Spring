package com.dw.dynamic.service;

import com.dw.dynamic.DTO.UserDTO;
import com.dw.dynamic.model.User;
import com.dw.dynamic.repository.AuthorityRepository;
import com.dw.dynamic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    AuthorityRepository authorityRepository;

    public UserDTO registerUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userDTO.getUserName());
        if (user.isPresent()) {
            throw new InvalidRequestException("Username already exists");
        }
        return userRepository.save(
                new User(
                        userDTO.getUserName(),
                        passwordEncoder.encode(userDTO.getPassword()),
                        userDTO.getEmail(),
                        userDTO.getRealName(),
                        authorityRepository.findById("USER")
                                .orElseThrow(()->new ResourceNotFoundException("No role")),
                        LocalDateTime.now())
        ).toDto();
    }

}
