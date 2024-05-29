package com.example.marketplacecc.dto;

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
}
