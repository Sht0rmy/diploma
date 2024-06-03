package com.example.marketplacecc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequiredArgsConstructor
public class BlogController {

    @GetMapping("/blog")
    public String blogIndex(){
        return "blog";
    }
}
