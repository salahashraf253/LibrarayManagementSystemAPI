package com.example.libraraymangementsystemapi.controller;

import com.example.libraraymangementsystemapi.dto.request.LoginRequest;
import com.example.libraraymangementsystemapi.dto.request.RegistrationRequest;
import com.example.libraraymangementsystemapi.dto.response.ApiResponse;
import com.example.libraraymangementsystemapi.dto.response.LoginResponse;
import com.example.libraraymangementsystemapi.dto.response.RegistrationResponse;
import com.example.libraraymangementsystemapi.enums.Role;
import com.example.libraraymangementsystemapi.service.AuthService;
import com.example.libraraymangementsystemapi.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register/borrower")
    public ResponseEntity<ApiResponse<RegistrationResponse>> registerBorrower(@RequestBody RegistrationRequest request) {
        request.setRole(Role.BORROWER);
        RegistrationResponse response = registrationService.register(request);

        ApiResponse<RegistrationResponse> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(), response);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<ApiResponse<RegistrationResponse>> registerAdmin(@RequestBody RegistrationRequest request) {
        request.setRole(Role.ADMIN);

        RegistrationResponse response = registrationService.register(request);
        ApiResponse<RegistrationResponse> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(), response);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}