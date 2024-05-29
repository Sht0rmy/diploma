package com.example.marketplacecc.repositories;

import com.example.marketplacecc.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepository extends JpaRepository<Order, Long> {
}
