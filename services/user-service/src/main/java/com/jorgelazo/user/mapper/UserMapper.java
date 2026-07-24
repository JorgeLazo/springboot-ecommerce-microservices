package com.jorgelazo.user.mapper;

import org.springframework.stereotype.Component;

import com.jorgelazo.user.dto.request.UserRequest;
import com.jorgelazo.user.dto.response.UserResponse;
import com.jorgelazo.user.entity.User;

@Component
public class UserMapper {


    public User toEntity(UserRequest userRequest) {

        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        
        return user;
    };

    public UserResponse toResponse(User user) {
        
        return new UserResponse(
            user.getId(), 
            user.getName(), 
            user.getEmail());
    }

}
