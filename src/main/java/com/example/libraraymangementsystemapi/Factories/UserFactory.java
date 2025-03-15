package com.example.libraraymangementsystemapi.Factories;

import com.example.libraraymangementsystemapi.dto.request.RegistrationRequest;
import com.example.libraraymangementsystemapi.entity.User;

public interface UserFactory {
    User createUser(RegistrationRequest registrationRequest);
}
