package com.example.libraraymangementsystemapi.factories;

import com.example.libraraymangementsystemapi.dto.request.RegistrationRequest;
import com.example.libraraymangementsystemapi.entity.Admin;
import com.example.libraraymangementsystemapi.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdminFactory implements UserFactory{
    private PasswordEncoder passwordEncoder;
    @Override
    public User createUser(RegistrationRequest registrationRequest) {
        return new Admin(
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getEmail(),
                passwordEncoder.encode(registrationRequest.getPassword())
        );
    }
}
