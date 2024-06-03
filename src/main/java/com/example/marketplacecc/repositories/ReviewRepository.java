package com.example.marketplacecc.repositories;

import com.example.marketplacecc.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ReviewRepository extends JpaRepository<Review, Long> {
}
