package com.dw.companyapp.service;

import com.dw.companyapp.model.MileageGrade;
import com.dw.companyapp.repository.MilegeGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class MileageGradeService {
    @Autowired
    MilegeGradeRepository milegeGradeRepository;

    public List<MileageGrade> getAllMileages() {
        return milegeGradeRepository.findAll();
    }
}
