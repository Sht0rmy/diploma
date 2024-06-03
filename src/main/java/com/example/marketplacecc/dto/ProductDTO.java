package com.example.marketplacecc.dto;

import com.example.marketplacecc.models.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String title;
    private double price;
    private String productType;
    private String description;
    private Long previewImage;
    private List<Long> images = new ArrayList<>();
    private LocalDateTime dateOfCreated;
    private int views;
    private int saves;
    private int baskets;
    private List<ReviewDTO> reviews = new ArrayList<>();
    private int rating;
}