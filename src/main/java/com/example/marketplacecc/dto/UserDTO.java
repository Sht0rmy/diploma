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
    private String nickname;
    private boolean active;
    private String role;
    private LocalDateTime dateOfCreated;
}
