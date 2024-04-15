package com.tsdev.identityservice.controller;

import com.tsdev.identityservice.dto.request.ApiRespone;
import com.tsdev.identityservice.dto.request.UserCreationRequest;
import com.tsdev.identityservice.dto.request.UserUpdateRequest;
import com.tsdev.identityservice.dto.response.UserResponse;
import com.tsdev.identityservice.entity.User;
import com.tsdev.identityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    ApiRespone<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiRespone<User> apiRespone = new ApiRespone<>();
        apiRespone.setResult(userService.createUser(request));
        return apiRespone;
    }

    @GetMapping
    List<User> getAllUsers() {
       return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }
    @PutMapping("/{userId}")
    UserResponse updateUser(@RequestBody UserUpdateRequest request, @PathVariable("userId") String userId){
        return userService.updateUser(userId, request);
    }
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User deleted";
    }
}
