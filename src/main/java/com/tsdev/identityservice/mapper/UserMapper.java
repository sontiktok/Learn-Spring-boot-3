package com.tsdev.identityservice.mapper;


import com.tsdev.identityservice.dto.request.UserCreationRequest;
import com.tsdev.identityservice.dto.request.UserUpdateRequest;
import com.tsdev.identityservice.dto.response.UserResponse;
import com.tsdev.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring") //
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);// map UserUpdateRequest vao User

}
