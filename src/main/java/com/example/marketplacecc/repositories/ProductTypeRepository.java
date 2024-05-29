package com.example.marketplacecc.repositories;

import com.example.marketplacecc.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    ProductType findByType(String type);
}
