package com.example.marketplacecc.api;

import com.example.marketplacecc.dto.ConverterDTO;
import com.example.marketplacecc.dto.ReviewDTO;
import com.example.marketplacecc.models.Product;
import com.example.marketplacecc.models.Review;
import com.example.marketplacecc.models.User;
import com.example.marketplacecc.services.ProductService;
import com.example.marketplacecc.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewAPIController {
    private final ProductService productService;
    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<String> createReview(@AuthenticationPrincipal User user, @RequestParam Long productId,
                                               @RequestBody ReviewDTO reviewDTO){
        Product product = productService.getById(productId);

        if(reviewDTO != null && product != null){
            Review review = new Review();
            review.setName(reviewDTO.getName());
            review.setRating(reviewDTO.getRating());
            review.setText(reviewDTO.getText());
            review.setProduct(product);
            review = reviewService.save(review);

            product.getReviews().add(review);
            product.updateRating();
            productService.save(product);
            return ResponseEntity.ok("Відгук успішно створено.");
        }
        return ResponseEntity.ok("Під час створення відгуку виникла помилка.");
    }

    @GetMapping("/get")
    public List<ReviewDTO> getAll(@RequestParam Long productId){
        Product product = productService.getById(productId);
        if(product != null){
            return ConverterDTO.reviewsToReviewsDTO(product.getReviews());
        }
        return new ArrayList<>();
    }
}
