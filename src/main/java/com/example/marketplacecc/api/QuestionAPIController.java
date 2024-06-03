package com.example.marketplacecc.api;

import com.example.marketplacecc.dto.ConverterDTO;
import com.example.marketplacecc.dto.QuestionDTO;
import com.example.marketplacecc.models.Product;
import com.example.marketplacecc.models.Question;
import com.example.marketplacecc.models.User;
import com.example.marketplacecc.services.ProductService;
import com.example.marketplacecc.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionAPIController {
    private final QuestionService questionService;
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@AuthenticationPrincipal User user, @RequestParam Long productId,
                                         @RequestBody QuestionDTO questionDTO){
        Product product = productService.getById(productId);
        if(product != null && questionDTO != null){
            Question question = new Question();
            question.setProduct(product);
            question.setQuestion(questionDTO.getQuestion());
            question.setName(questionDTO.getName());
            question = questionService.save(question);

            product.getQuestions().add(question);
            productService.save(product);

            return ResponseEntity.ok("Питання успішно створено.");
        }
        return ResponseEntity.ok("Під час створення питання виникла помилка.");
    }

    @GetMapping("/get")
    public List<QuestionDTO> get(@RequestParam Long productId){
        Product product = productService.getById(productId);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        if(product != null){
            questionDTOS = ConverterDTO.questionsToQuestionsDTO(product.getQuestions());
        }
        return questionDTOS;
    }

    @PostMapping("/answer")
    public ResponseEntity<String> answer(@AuthenticationPrincipal User user, @RequestParam Long questionId,
                                         @RequestBody String answer){
        Question question = questionService.getById(questionId);
        if(question != null){
            question.setAnswer(answer);
            questionService.save(question);
            return ResponseEntity.ok("Відповідь успішно додано.");
        }
        return ResponseEntity.ok("Під час створення відповіді виникла помилка.");
    }
}