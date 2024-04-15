package com.tsdev.identityservice.dto.request;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // Giúp tạo ra đối tượng dễ dàng hơn, thích truyền bao nhiêu đối số tùy
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
     String username;
    @Size(min = 8, message = "INVALID_PASSWORD")
     String password;
     String firstName;
     String lastName;
     LocalDate dob;
}
