package com.example.marketplacecc.services;

import com.example.marketplacecc.models.Post;
import com.example.marketplacecc.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post save(Post post){
        return postRepository.save(post);
    }

    public List<Post> getAll(){
        return postRepository.findAll();
    }
}
