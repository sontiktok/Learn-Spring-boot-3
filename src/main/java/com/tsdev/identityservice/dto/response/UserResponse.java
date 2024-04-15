package com.tsdev.identityservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // Giúp tạo ra đối tượng dễ dàng hơn, thích truyền bao nhiêu đối số tùy
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
     String id;
     String username;
     String password;
     String firstName;
     String lastName;
     LocalDate dob;
}
