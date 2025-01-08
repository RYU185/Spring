package com.dw.dynamic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name="user_name")
    private String userName;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name = "real_name", nullable = false)
    private String realName;

    @Column(name = "business_type")
    private String businessType;

    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_login")
    private LocalDateTime lastLoginDate;

    @ManyToOne
    @JoinColumn(name = "user_authority")
    private Authority authority;

}
