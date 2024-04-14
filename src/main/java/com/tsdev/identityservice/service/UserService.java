package com.tsdev.identityservice.service;

import com.tsdev.identityservice.dto.request.UserCreationRequest;
import com.tsdev.identityservice.dto.request.UserUpdateRequest;
import com.tsdev.identityservice.entity.User;
import com.tsdev.identityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createUser(UserCreationRequest request){
        User user = new User();
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(String userId, UserUpdateRequest request){
       // Su dung method duoi de lay ra user
        User user = getUser(userId);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        user.setPassword(request.getPassword());
        return userRepository.save(user);
    }
    public User getUser(String id){
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User Not Found"));
    }
    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
