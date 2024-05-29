package com.example.marketplacecc.controllers;

import com.example.marketplacecc.models.User;
import com.example.marketplacecc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

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
}
