package com.dw.dynamic.service;

import com.dw.dynamic.DTO.CourseDTO;
import com.dw.dynamic.DTO.ProductDTO;
import com.dw.dynamic.exception.PermissionDeniedException;
import com.dw.dynamic.exception.ResourceNotFoundException;
import com.dw.dynamic.model.Course;
import com.dw.dynamic.model.User;
import com.dw.dynamic.repository.CourseRepository;
import com.dw.dynamic.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll().stream().toList();
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 제품ID입니다 : " + id));
    }

    public List<Course> getCoursesByTitle(String title) {
        return courseRepository.findByTitleLike("%" + title + "%").stream().toList();
    }

    public Course saveCourse(Course course, HttpServletRequest request) {
        User currentUser = userService.getCurrentUser(request);
        if (!currentUser.getAuthority().getAuthorityName().equals("ADMIN")) {
            throw new PermissionDeniedException("권한이 없습니다.");
          }
            return courseRepository.findById(course.getId())
                    .map(c-> courseRepository.save(course))
                    .orElseThrow(()-> new ResourceNotFoundException("없는 제품 ID입니다."));
        }


        public String deleteCourse (String title, HttpServletRequest request){
            User currentUser = userService.getCurrentUser(request);
            if (!currentUser.getAuthority().getAuthorityName().equals("ADMIN")) {
                throw new PermissionDeniedException("권한이 없습니다.");
            }
            if (courseRepository.findByTitle(title).isEmpty()){
                throw new ResourceNotFoundException("존재하지 않는 강의명입니다");
            }
            courseRepository.deleteByTitle(title);
            return "강의명 : "+title + "이(가) 정상 삭제되었습니다";
        }
    }


