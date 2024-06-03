package com.example.marketplacecc.services;

import com.example.marketplacecc.models.Review;
import com.example.marketplacecc.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review save(Review review){
        return reviewRepository.save(review);
    }

    public List<Review> getAll(){
        return reviewRepository.findAll();
    }
}
