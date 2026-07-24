package com.jorgelazo.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jorgelazo.user.dto.request.UserRequest;
import com.jorgelazo.user.dto.response.UserResponse;
import com.jorgelazo.user.entity.User;
import com.jorgelazo.user.exception.UserNotFoundException;
import com.jorgelazo.user.mapper.UserMapper;
import com.jorgelazo.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UserResponse> findAll(){

        return repository.findAll()
        .stream()
        .map(mapper::toResponse)
        .toList();
    }

    public UserResponse save(UserRequest userRequest){

        User user = mapper.toEntity(userRequest);
        
        User savedUser = repository.save(user);

        return mapper.toResponse(savedUser);
    }

    public UserResponse findById(Long id){

        User user = getUser(id);

        return mapper.toResponse(user);
    }

    public UserResponse update(Long id, UserRequest userRequest){

        User user = getUser(id);

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());

        User updatedUser = repository.save(user);

        return mapper.toResponse(updatedUser);
    }

    public void delete(Long id){

        User user = getUser(id);

        repository.delete(user);
    }

    private User getUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " was not found"));
    }
}
