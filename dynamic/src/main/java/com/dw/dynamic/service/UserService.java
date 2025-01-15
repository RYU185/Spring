package com.dw.dynamic.service;
import com.dw.dynamic.DTO.UserDTO;
import com.dw.dynamic.enums.Gender;
import com.dw.dynamic.exception.UnauthorizedUserException;
import com.dw.dynamic.model.User;
import com.dw.dynamic.repository.AuthorityRepository;
import com.dw.dynamic.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.dw.dynamic.exception.InvalidRequestException;
import com.dw.dynamic.exception.ResourceNotFoundException;

import java.io.InvalidClassException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    AuthorityRepository authorityRepository;

    public UserDTO registerUser(UserDTO userDTO) {  // 회원가입: 새로운 사용자를 등록
        Optional<User> user = userRepository.findById(userDTO.getUserName());
        if (user.isPresent()) {
            throw new InvalidRequestException("already exist ID");
        }

        Gender gender;
        try {
            gender = Gender.valueOf(userDTO.getGender().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Invalid gender value: " + userDTO.getGender());
        }

        return userRepository.save(
                new User(
                        userDTO.getUserName(),
                        userDTO.getCompanyName(),
                        userDTO.getRealName(),
                        passwordEncoder.encode(userDTO.getPassword()),
                        gender,
                        userDTO.getEmail(),
                        userDTO.getPhoneNumber(),
                        userDTO.getBusinessNumber(),
                        userDTO.getBusinessType(),
                        userDTO.isExistBusinessOperator(),
                        userDTO.getPoint(),
                        authorityRepository.findById("USER")
                                .orElseThrow(()-> new ResourceNotFoundException("No role"))
                        )
        ).toDTO();
    }

    public boolean validateUser(String username, String password) {
        User user = userRepository.findById(username)
                .orElseThrow(()->new InvalidRequestException("Invalid Username"));
        return passwordEncoder.matches(password, user.getPassword());
    }

    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // 세션이 없으면 예외 처리
        if (session == null) {
            throw new UnauthorizedUserException("No Session exist");
        }
        String userName = (String) session.getAttribute("username");  // 세션에서 유저네임 반환
        return userRepository.findById(userName)
                .orElseThrow(()->new InvalidRequestException("No username"));
    }

    public List<UserDTO> getAllUsers(){ // 전체 유저 조회
        return userRepository.findAll().stream().map(User::toDTO).toList();
    }

    public UserDTO getUserById(String id){ //id를 통한 유저 조회
        return userRepository.findById(id).map(User::toDTO).orElseThrow(()-> new ResourceNotFoundException("존재하지 않는 ID 입니다"));
    }

    public List<UserDTO> getUserByRealName(String realName){ // realName을 통한 유저 조회
        return userRepository.findByRealNameLike("%"+realName+"%").stream().map(User::toDTO).toList();
    }

    public List<UserDTO> getUserByExistBusinessOperator(boolean existBusinessOperator){
        return userRepository.findUserByExistBusinessOperator(existBusinessOperator)
                .stream().map(User::toDTO).toList();
    }

    public UserDTO getIdByEmail(String email){ // 이메일로 통하여 아이디 찾기
        return userRepository.findByEmail(email).toDTO();
    }

//    public UserDTO ModifyPwByIDAndPhoneNumber(String id, String phoneNumber){
//      아이디와 전화번호를 통해 임시비밀번호 전송하여 비밀번호 수정
//        return userRepository.
//    }

    public UserDTO ModifyUserData(UserDTO userDTO){
        return userRepository.findById(userDTO.getUserName())
                .map((user) ->{
                    return userRepository.save(user).toDTO();
                })
                .orElseGet(()->{
                    User user = new User(
                            null,
                            userDTO.getEmail(),
                            userDTO.getPhoneNumber()
                    )
                })
    }
}
