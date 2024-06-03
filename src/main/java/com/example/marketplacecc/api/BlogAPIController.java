package com.example.marketplacecc.api;

import com.example.marketplacecc.dto.ConverterDTO;
import com.example.marketplacecc.dto.PostDTO;
import com.example.marketplacecc.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class BlogAPIController {
    private final PostService postService;

    @GetMapping("/get-all")
    public List<PostDTO> getAll(){
        List<PostDTO> postDTOS = ConverterDTO.postsToPostsDTO(postService.getAll());
        System.out.println(postDTOS.size());
        return postDTOS;
    }
}
