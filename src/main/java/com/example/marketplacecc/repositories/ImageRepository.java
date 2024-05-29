package com.example.marketplacecc.repositories;

import com.example.marketplacecc.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Long> {
}
