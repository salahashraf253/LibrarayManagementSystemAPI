package com.example.libraraymangementsystemapi.service;


import com.example.libraraymangementsystemapi.dto.request.LoginRequest;
import com.example.libraraymangementsystemapi.dto.response.LoginResponse;
import com.example.libraraymangementsystemapi.enums.Role;
import com.example.libraraymangementsystemapi.exception.ResourceNotFoundException;
import com.example.libraraymangementsystemapi.repository.AdminRepository;
import com.example.libraraymangementsystemapi.repository.BorrowerRepository;
import com.example.libraraymangementsystemapi.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private AdminRepository adminRepository;

    private BorrowerRepository borrowerRepository;

    public LoginResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Role role;
        Long id;
        if (adminRepository.findByEmail(request.getEmail()).isPresent()) {
            role = Role.ADMIN;
            id = adminRepository.findIdByEmail(request.getEmail());
        } else if (borrowerRepository.findByEmail(request.getEmail()).isPresent()) {
            role = Role.BORROWER;
            id = borrowerRepository.findIdByEmail(request.getEmail());
        } else {
            throw new ResourceNotFoundException("User not found");
        }
        String token = jwtUtil.generateToken(userDetails,id);
        return new LoginResponse(token, role);
    }
}