package com.example.marketplacecc.repositories;

import com.example.marketplacecc.models.Product;
import com.example.marketplacecc.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductType(ProductType productType);
    List<Product> findTop3ByOrderByDateOfCreatedDesc();
    List<Product> findTop6ByOrderByDateOfCreatedDesc();
    List<Product> findByTitleContaining(String name);
}
