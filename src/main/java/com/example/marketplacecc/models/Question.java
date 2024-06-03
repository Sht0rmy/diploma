package com.example.marketplacecc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@Entity
@Table(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 5000)
    private String question;
    @Column(length = 5000)
    private String answer;
    @ManyToOne
    private Product product;
    private LocalDateTime dateOfCreated = LocalDateTime.now();
}
