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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    public UserResponse createUser(UserCreationRequest request){
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        //Map request vao User
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Luu vao DB và mapper nó thành response
        return userMapper.toUserResponse(userRepository.save(user));
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
