package com.example.libraraymangementsystemapi.Factories;

import com.example.libraraymangementsystemapi.dto.request.RegistrationRequest;
import com.example.libraraymangementsystemapi.entity.Borrower;
import com.example.libraraymangementsystemapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BorrowerFactory implements UserFactory {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User createUser(RegistrationRequest registrationRequest) {
        return new Borrower(
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getEmail(),
                passwordEncoder.encode(registrationRequest.getPassword())
        );
    }
}