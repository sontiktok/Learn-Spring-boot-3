package com.tsdev.identityservice.service;
import com.tsdev.identityservice.dto.request.UserCreationRequest;
import com.tsdev.identityservice.dto.request.UserUpdateRequest;
import com.tsdev.identityservice.dto.response.UserResponse;
import com.tsdev.identityservice.entity.User;
import com.tsdev.identityservice.exception.AppException;
import com.tsdev.identityservice.exception.ErrorCode;
import com.tsdev.identityservice.mapper.UserMapper;
import com.tsdev.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    public User createUser(UserCreationRequest request){
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        //Map request vao User
        User user = userMapper.toUser(request);
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());
        // Luu vao DB
        return userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
       // Su dung method duoi de lay ra user
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        userMapper.updateUser(user,request);

        return userMapper.toUserResponse(userRepository.save(user));
    }
    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found")));
    }
    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
