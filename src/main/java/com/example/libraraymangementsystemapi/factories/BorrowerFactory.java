package com.example.libraraymangementsystemapi.factories;

import com.example.libraraymangementsystemapi.dto.request.RegistrationRequest;
import com.example.libraraymangementsystemapi.entity.Borrower;
import com.example.libraraymangementsystemapi.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class BorrowerFactory implements UserFactory {
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