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
public class CartItem {

    @Id
    @Column(name = "cart_item_id")
    private String cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "addedDate", updatable = false)
    private LocalDateTime addedDate;
}
