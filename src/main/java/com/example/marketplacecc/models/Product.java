package com.example.marketplacecc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Table(name = "products")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double price;
    @ManyToOne
    private ProductType productType;
    @Column(length = 1000)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private List<Image> images = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    private Image previewImage;
    private LocalDateTime dateOfCreated;
    private int views = 0;
    private int saves = 0;
    private int baskets = 0;
    @OneToMany
    private List<Review> reviews = new ArrayList<>();
    private int rating = 0;
    @OneToMany
    private List<Question> questions = new ArrayList<>();
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", productType=" +
                ", description='" + description + '\'' +
                ", images=" + images +
                ", previewImage=" + previewImage +
                ", dateOfCreated=" + dateOfCreated +
                '}';
    }

    public void updateRating(){
        if(reviews.size() > 1){
            int average = 0;
            int count = 0;
            for (Review review : reviews) {
                count++;
                average += review.getRating();
            }

            rating = average/count;
        }
    }
}
