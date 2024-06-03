package com.example.marketplacecc.controllers;

import com.example.marketplacecc.models.Image;
import com.example.marketplacecc.models.User;
import com.example.marketplacecc.services.ImageService;
import com.example.marketplacecc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;



@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final ImageService imageService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(User user){
        try{
            if(userService.createUser(user) != null){
                return ("redirect:/login");
            }else{
                return ("redirect:/registration");
            }
        }catch(Exception ex){
            return ("redirect:/registration");
        }
    }

    @GetMapping("/cabinet")
    public String cabinet(){
        return "cabinet";
    }

    @GetMapping("/change-data")
    public String changeData(){
        return "changeData";
    }

    @PostMapping("/change-avatar")
    public String changeAvatar(@AuthenticationPrincipal User user, @RequestParam("file1") MultipartFile file1) throws IOException {
        if(file1 != null){
            Image image = imageService.save(file1);
            user = userService.getUserById(user.getId());
            user.setAvatar(image);
            userService.save(user);
        }

        return "redirect:/cabinet";
    }

    @PostMapping("/change-info")
    public String changeInfo(@AuthenticationPrincipal User user, @RequestParam("pib") String pib, @RequestParam("delivery") String delivery,
                             @RequestParam("phone") String phone, @RequestParam("email") String email){
        user = userService.getUserById(user.getId());
        if(pib != null)
            user.setName(pib);
        if(delivery != null)
            user.setDelivery(delivery);
        if(phone != null)
            user.setPhone(phone);
        if(email != null)
            user.setEmail(email);

        userService.save(user);

        return "redirect:/cabinet";
    }

    @GetMapping("/my-orders")
    public String myOrders(){
        return "myOrders";
    }
}