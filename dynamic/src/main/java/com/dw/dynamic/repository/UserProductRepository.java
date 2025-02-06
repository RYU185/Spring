package com.dw.dynamic.repository;

import com.dw.dynamic.model.PayrollSubscription;
import com.dw.dynamic.model.Product;
import com.dw.dynamic.model.User;
import com.dw.dynamic.model.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserProductRepository extends JpaRepository<UserProduct,Long> {
    List<UserProduct> findByUser(User currentUser);

     UserProduct findByProductId( String productId);

    @Query("select up from UserProduct up " +
            "join up.product p " +
            "left join Course c on p.id = c.id " +
            "left join PayrollSubscription ps on p.id = ps.id " +
            "where up.user.userName = :currentUser " +
            "and (c.title like %:productName% or ps.title like %:productName%)")
    List<UserProduct> findByProductNameLike(User currentUser, String productName);

    @Query("select ps from UserProduct us join PayrollSubscription ps on us.product.id = ps.id ")
    List<PayrollSubscription> findPayrollSubscriptionByProduct();

    @Query("select u from UserProduct u join u.product p where type(p) = PayrollSubscription and p.expireDate < :expireDate")
    List<UserProduct> findExpiredProducts(LocalDate expireDate);
}
