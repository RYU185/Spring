package com.dw.dynamic.repository;

import com.dw.dynamic.model.User;
import com.dw.dynamic.model.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserProductRepository extends JpaRepository<UserProduct,Long> {
    List<UserProduct> findByUser(User currentUser);

    @Query("select up from UserProduct up " +
            "left join Course c on up.product.id = c.id " +
            "left join PayrollSubscription ps on up.product.id = ps.id " +
            "where (c.title like %:productName%) OR (ps.title like %:productName%)")
    List<UserProduct> findByProductNameLike(String productName);
}
