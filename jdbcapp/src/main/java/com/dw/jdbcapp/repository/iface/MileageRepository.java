package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.MileGrade;

import java.util.List;

public interface MileageRepository {
    List<MileGrade> getAllMileage();
}
