package com.example.marketplacecc.dto;

import lombok.Data;

import java.util.List;



@Data
public class OrderDTO {
    private List<ProductDTO> products;
    private String pib;
    private String delivery;
    private String phone;
    private String email;
    private String date;
}
