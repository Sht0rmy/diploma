package com.example.marketplacecc.repositories;

import com.example.marketplacecc.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post, Long> {
}
