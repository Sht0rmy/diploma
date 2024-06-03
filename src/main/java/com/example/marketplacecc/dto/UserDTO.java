package com.example.marketplacecc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String nickname;
    private String delivery;
    private String phone;
    private Long avatar;
    private boolean active;
    private String role;
    private LocalDateTime dateOfCreated;
}
