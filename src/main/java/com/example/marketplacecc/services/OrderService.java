package com.example.marketplacecc.services;

import com.example.marketplacecc.models.Order;
import com.example.marketplacecc.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sh1chiro on 09.05.2024.
 * <p>
 * When I wrote this code, only god and
 * I knew how it worked.
 * Now, only god knows it!
 *
 * @author Sh1chiro
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public Order save(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order getById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public void delete(Order order){
        orderRepository.delete(order);
    }
}
