package com.tsdev.identityservice.controller;

import com.nimbusds.jose.JOSEException;
import com.tsdev.identityservice.dto.request.ApiRespone;
import com.tsdev.identityservice.dto.request.AuthenticationRequest;
import com.tsdev.identityservice.dto.request.IntrospectRequest;
import com.tsdev.identityservice.dto.response.AuthenticationResponse;
import com.tsdev.identityservice.dto.response.IntrospectResponse;
import com.tsdev.identityservice.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiRespone<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);

        return ApiRespone.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiRespone<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiRespone.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
