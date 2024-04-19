package com.tsdev.identityservice.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//Field nao null se khong co trong class
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiRespone <T> {
     @Builder.Default
     int code = 1000;
     String message;
     T result;

}
