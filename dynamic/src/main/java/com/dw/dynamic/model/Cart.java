package com.dw.dynamic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Entity
public class Cart {

    @Id
    @Column(name = "cart_id")
    private String cartId;

    @OneToOne
    @JoinColumn(name = "user_name")
    private User user;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
