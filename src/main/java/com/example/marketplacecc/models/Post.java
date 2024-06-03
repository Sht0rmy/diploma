package com.example.marketplacecc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 5000)
    private String text;
    @OneToOne
    private Image image;
    private LocalDateTime dateOfCreated = LocalDateTime.now();
}
