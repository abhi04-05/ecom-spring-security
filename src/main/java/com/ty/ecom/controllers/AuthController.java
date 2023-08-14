package com.ty.ecom.controllers;

import com.ty.ecom.pojos.EcomResponse;
import com.ty.ecom.pojos.EmailLoginRequest;
import com.ty.ecom.pojos.SignupRequest;
import com.ty.ecom.pojos.UsernameLoginRequest;
import com.ty.ecom.services.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "ecom-api")
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/username-login")
    public ResponseEntity<EcomResponse> loginByUsername(@Valid @RequestBody UsernameLoginRequest usernameLoginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.loginByUsername(usernameLoginRequest));
    }

    @PostMapping("/email-login")
    public ResponseEntity<EcomResponse> loginByEmail(@Valid @RequestBody EmailLoginRequest emailLoginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.loginByEmail(emailLoginRequest));
    }

    @PostMapping("/user-registration")
    public ResponseEntity<EcomResponse> userSignUp(@Valid @RequestBody SignupRequest signupRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.userSignUp(signupRequest));
    }

    @PostMapping("/admin-registration")
    public ResponseEntity<EcomResponse> adminSignUp(@Valid @RequestBody SignupRequest signupRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.adminSignUp(signupRequest));
    }

}
