package com.simplon.service;

import com.simplon.dto.UserRequest;
import com.simplon.dto.UserResponse;
import com.simplon.model.User;
import com.simplon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;




    public void createUser(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .category((userRequest.getCategory()))
                .amount((userRequest.getAmount()))
                .build();

        userRepository.save(user);
        log.info("User {} is created", user.getId());
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("All users are listed");
        return  users.stream().map(this::mapToUserResponse).toList();
    }

    private UserResponse mapToUserResponse(User user) { // mapToUserResponse is a method that takes a User object and returns a UserResponse object
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .category(user.getCategory())
                .amount(user.getAmount())
                .build();
    }
}

