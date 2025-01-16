package com.dw.dynamic.repository;

import com.dw.dynamic.model.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory,Long> {

//    @Query("select ph from PurchaseHistory ph where ph.product (select c from Course c where c.title like %:title%)")
//    List<PurchaseHistory> findByCourseTitleLike(String title);
//
//    List
}
