package com.example.marketplacecc.dto;

import com.example.marketplacecc.enums.Roles;
import com.example.marketplacecc.models.Image;
import com.example.marketplacecc.models.Product;
import com.example.marketplacecc.models.User;

import java.util.ArrayList;
import java.util.List;


public class ConverterDTO {
    public static List<ProductDTO> productListToDTO(List<Product> products){
        List<ProductDTO> productDTOs = new ArrayList<>();
        if(!products.isEmpty()){
            for (Product product : products) {
                productDTOs.add(productToDTO(product));
            }
        }
        return productDTOs;
    }

    public static ProductDTO productToDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        if(product != null){
            productDTO.setId(product.getId());
            productDTO.setTitle(product.getTitle());
            productDTO.setPrice(product.getPrice());
            productDTO.setProductType(product.getProductType().getType());
            productDTO.setDescription(product.getDescription());
            productDTO.setPreviewImage(product.getPreviewImage().getId());
            for (Image image : product.getImages()) {
                productDTO.getImages().add(image.getId());
            }
            productDTO.setDateOfCreated(product.getDateOfCreated());
        }
        return productDTO;
    }

    public static List<UserDTO> userListToDTO(List<User> users){
        List<UserDTO> userDTOs = new ArrayList<>();
        if(!users.isEmpty()){
            for (User user : users) {
                userDTOs.add(userToDTO(user));
            }
        }
        return userDTOs;
    }

    public static UserDTO userToDTO(User user){
        UserDTO userDTO = new UserDTO();
        if(user != null){
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setNickname(user.getNickname());
            userDTO.setActive(user.isActive());
            for (Roles role : user.getRoles()) {
                userDTO.setRole(role.toString());
            }
            userDTO.setDateOfCreated(user.getDateOfCreated());
        }
        return userDTO;
    }
}
