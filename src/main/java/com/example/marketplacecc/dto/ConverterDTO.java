package com.example.marketplacecc.dto;

import com.example.marketplacecc.enums.Roles;
import com.example.marketplacecc.models.*;

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
            productDTO.setViews(product.getViews());
            productDTO.setSaves(product.getSaves());
            productDTO.setBaskets(product.getBaskets());
            productDTO.setReviews(reviewsToReviewsDTO(product.getReviews()));
            productDTO.setRating(product.getRating());
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
            userDTO.setName(user.getName() != null ? user.getName() : "Не знайдено даних про ім'я");
            userDTO.setDelivery(user.getDelivery() != null ? user.getDelivery() : "Не знайдено даних про адресу");
            userDTO.setPhone(user.getPhone() != null ? user.getPhone() : "Не знайдено даних про телефон");
            userDTO.setAvatar(user.getAvatar() != null ? user.getAvatar().getId() : -1);

            for (Roles role : user.getRoles()) {
                userDTO.setRole(role.toString());
            }
            userDTO.setDateOfCreated(user.getDateOfCreated());
        }
        return userDTO;
    }

    public static List<OrderDTO> ordersToOrdersDTO(List<Order> orders){
        List<OrderDTO> orderDTOS = new ArrayList<>();

        if(!orders.isEmpty()){
            for (Order order : orders) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setProducts(productListToDTO(order.getProducts()));
                orderDTO.setPib(order.getName());
                orderDTO.setEmail(order.getEmail());
                orderDTO.setPhone(order.getPhone());
                orderDTO.setDelivery(order.getDelivery());
                orderDTO.setDate(order.getDateOfCreated().toLocalDate().toString());
                orderDTOS.add(orderDTO);
            }
        }

        return orderDTOS;
    }

    public static List<PostDTO> postsToPostsDTO(List<Post> posts){
        List<PostDTO> postDTOS = new ArrayList<>();
        if(!posts.isEmpty()){
            for (Post post : posts) {
                PostDTO postDTO = new PostDTO();
                postDTO.setText(post.getText());
                postDTO.setImage(post.getImage().getId());

                postDTOS.add(postDTO);
            }
        }

        return postDTOS;
    }

    public static List<ReviewDTO> reviewsToReviewsDTO(List<Review> reviews){
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        if(!reviews.isEmpty()){
            for (Review review : reviews) {
                reviewDTOS.add(reviewToReviewDTO(review));
            }
        }
        return reviewDTOS;
    }

    public static ReviewDTO reviewToReviewDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();

        if(review != null){
            reviewDTO.setName(review.getName());
            reviewDTO.setText(review.getText());
            reviewDTO.setRating(review.getRating());
        }

        return reviewDTO;
    }

    public static List<QuestionDTO> questionsToQuestionsDTO(List<Question> questions){
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        if(!questions.isEmpty()){
            for (Question question : questions) {
                questionDTOS.add(questionToQuestionDTO(question));
            }
        }

        return questionDTOS;
    }

    public static QuestionDTO questionToQuestionDTO(Question question){
        QuestionDTO questionDTO = new QuestionDTO();

        if(question != null){
            questionDTO.setId(question.getId());
            questionDTO.setQuestion(question.getQuestion());
            questionDTO.setName(question.getName());
            questionDTO.setAnswer(question.getAnswer());
        }

        return questionDTO;
    }
}
