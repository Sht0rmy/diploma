package com.example.marketplacecc.api;

import com.example.marketplacecc.dto.ConverterDTO;
import com.example.marketplacecc.dto.ProductDTO;
import com.example.marketplacecc.dto.UserDTO;
import com.example.marketplacecc.enums.Roles;
import com.example.marketplacecc.models.Product;
import com.example.marketplacecc.models.User;
import com.example.marketplacecc.services.ProductService;
import com.example.marketplacecc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserAPIController {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/get-all")
    public List<UserDTO> getAllUsers(@AuthenticationPrincipal User user){
//        User admin = userService.getUserById(user.getId());
//        List<UserDTO> users = new ArrayList<>();
//        if(admin.getRoles().contains(Roles.ADMIN)){
//            users = ConverterDTO.userListToDTO(userService.getAll());
//        }
//        return users;
        return ConverterDTO.userListToDTO(userService.getAll());
    }

    @GetMapping("/get-me")
    public UserDTO getMe(@AuthenticationPrincipal User user){
        return ConverterDTO.userToDTO(user);
    }

    @GetMapping("/get-my-wish")
    public List<ProductDTO> getMyWishList(@AuthenticationPrincipal User user){
        return ConverterDTO.productListToDTO(user.getWishList());
    }

    @GetMapping("/get-basket")
    public List<ProductDTO> getMyBasket(@AuthenticationPrincipal User user){
        return ConverterDTO.productListToDTO(user.getBasket());
    }

    @PostMapping("/add-to-wish")
    public HttpStatus addToWishList(@AuthenticationPrincipal User user, @RequestParam Long productId){
        user = userService.getUserById(user.getId());
        if(user != null){
            Product product = productService.getById(productId);
            if(product != null){
                user.getWishList().add(product);
                userService.save(user);
                return HttpStatus.OK;
            }
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @PostMapping("/add-to-basket")
    public HttpStatus addToBasket(@AuthenticationPrincipal User user, @RequestParam Long productId){
        user = userService.getUserById(user.getId());
        if(user != null){
            Product product = productService.getById(productId);
            if(product != null){
                user.getBasket().add(product);
                userService.save(user);
                return HttpStatus.OK;
            }
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.BAD_REQUEST;
    }

}
