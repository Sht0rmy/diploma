package com.example.marketplacecc.services;

import com.example.marketplacecc.models.Question;
import com.example.marketplacecc.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question save(Question question){
        return questionRepository.save(question);
    }

    public Question getById(Long id){
        return questionRepository.findById(id).orElse(null);
    }
}
