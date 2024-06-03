package com.example.marketplacecc.dto;

import lombok.Data;



@Data
public class QuestionDTO {
    private Long id;
    private String name;
    private String question;
    private String answer;
}
