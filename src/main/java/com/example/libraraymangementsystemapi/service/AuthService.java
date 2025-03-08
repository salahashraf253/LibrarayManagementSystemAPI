package com.example.libraraymangementsystemapi.service;


import com.example.libraraymangementsystemapi.dto.request.LoginRequest;
import com.example.libraraymangementsystemapi.dto.response.LoginResponse;
import com.example.libraraymangementsystemapi.enums.Role;
import com.example.libraraymangementsystemapi.repository.AdminRepository;
import com.example.libraraymangementsystemapi.repository.BorrowerRepository;
import com.example.libraraymangementsystemapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    public LoginResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));


        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        Role role;
        if (adminRepository.findByEmail(request.getEmail()).isPresent()) {
            role = Role.ADMIN;
        } else if (borrowerRepository.findByEmail(request.getEmail()).isPresent()) {
            role = Role.BORROWER;
        } else {
            throw new RuntimeException("User not found");
        }
        return new LoginResponse(token, role);
    }
}