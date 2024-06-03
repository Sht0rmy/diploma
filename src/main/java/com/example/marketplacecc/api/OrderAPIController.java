package com.example.marketplacecc.api;

import com.example.marketplacecc.dto.ConverterDTO;
import com.example.marketplacecc.dto.OrderDTO;
import com.example.marketplacecc.dto.ProductDTO;
import com.example.marketplacecc.models.Order;
import com.example.marketplacecc.models.Product;
import com.example.marketplacecc.models.User;
import com.example.marketplacecc.services.OrderService;
import com.example.marketplacecc.services.ProductService;
import com.example.marketplacecc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderAPIController {
    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@AuthenticationPrincipal User user, @RequestBody OrderDTO orderDTO){
        user = userService.getUserById(user.getId());

        if(orderDTO != null){
            Order order = new Order();
            List<Product> products = new ArrayList<>();
            for (ProductDTO productDTO : orderDTO.getProducts()) {
                Product product = productService.getById(productDTO.getId());
                if(product != null)
                    products.add(product);
            }
            order.setProducts(products);
            order.setName(orderDTO.getPib());
            order.setPhone(orderDTO.getPhone());
            order.setDelivery(orderDTO.getDelivery());
            order.setEmail(orderDTO.getEmail());

            order = orderService.save(order);

            user.getOrders().add(order);
            userService.save(user);
            return ResponseEntity.ok("Замовлення успішно створено!");
        }
        return ResponseEntity.ok("Під час створення замовлення виникла помилка. Спробуйте пізніше.");
    }

    @GetMapping("/my-orders")
    public List<OrderDTO> myOrders(@AuthenticationPrincipal User user){
        user = userService.getUserById(user.getId());

        List<OrderDTO> orderDTOS = new ArrayList<>();
        if(!user.getOrders().isEmpty()){
            orderDTOS = ConverterDTO.ordersToOrdersDTO(user.getOrders());
        }

        return orderDTOS;
    }

    @GetMapping("/get-all")
    public List<OrderDTO> allOrders(@AuthenticationPrincipal User user){
        return ConverterDTO.ordersToOrdersDTO(orderService.getAll());
    }
}
