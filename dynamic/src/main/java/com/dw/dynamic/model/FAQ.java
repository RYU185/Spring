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
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String FAQId;

    @Column(name = "question")
    private String question;

    @Column(name = "answer", nullable = false, length = 3000)
    private String answer;

    @Column(name = "add_date", updatable = false)
    private LocalDateTime addDate;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;
}
