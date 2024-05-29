package com.example.marketplacecc.services;

import com.example.marketplacecc.enums.Roles;
import com.example.marketplacecc.models.User;
import com.example.marketplacecc.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User save(User user){
        return userRepository.save(user);
    }

    public User createUser(User user){
        if(userRepository.findByEmail(user.getEmail()) == null) {
            user.setActive(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (user.getEmail().equals("admin@admin")) {
                user.getRoles().add(Roles.ADMIN);
            } else {
                user.getRoles().add(Roles.USER);
            }

            userRepository.save(user);
        }
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
