package com.example.libraraymangementsystemapi.controller;

import com.example.libraraymangementsystemapi.dto.request.LoginRequest;
import com.example.libraraymangementsystemapi.dto.request.RegistrationRequest;
import com.example.libraraymangementsystemapi.dto.response.ApiResponse;
import com.example.libraraymangementsystemapi.dto.response.LoginResponse;
import com.example.libraraymangementsystemapi.dto.response.RegistrationResponse;
import com.example.libraraymangementsystemapi.enums.Role;
import com.example.libraraymangementsystemapi.service.AuthService;
import com.example.libraraymangementsystemapi.service.RegistrationService;
import com.example.libraraymangementsystemapi.util.ExtraDataUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private RegistrationService registrationService;
    private ExtraDataUtil extraDataUtil;
    private AuthService authService;

    @PostMapping("/register/borrower")
    public ResponseEntity<ApiResponse<RegistrationResponse>> registerBorrower(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @RequestBody RegistrationRequest request) {
        request.setRole(Role.BORROWER);
        return extraDataUtil.buildResponse(httpRequest, includeExtraData, registrationService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<ApiResponse<RegistrationResponse>> registerAdmin(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @RequestBody RegistrationRequest request) {
        request.setRole(Role.ADMIN);
        return extraDataUtil.buildResponse(httpRequest, includeExtraData, registrationService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "false") boolean includeExtraData,
            @RequestBody LoginRequest request) {
        return extraDataUtil.buildResponse(httpRequest, includeExtraData, authService.login(request), HttpStatus.OK);
    }

}