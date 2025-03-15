package com.example.libraraymangementsystemapi.Mappers;

import com.example.libraraymangementsystemapi.Factories.UserFactory;
import com.example.libraraymangementsystemapi.Factories.UserFactoryProvider;
import com.example.libraraymangementsystemapi.dto.request.RegistrationRequest;
import com.example.libraraymangementsystemapi.dto.response.RegistrationResponse;
import com.example.libraraymangementsystemapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private UserFactoryProvider userFactoryProvider;

    public RegistrationResponse sourceToDestination(User user) {
        return new RegistrationResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public User destinationToSource(RegistrationRequest registrationRequest) {
        UserFactory userFactory = userFactoryProvider.getUserFactory(registrationRequest.getRole());
        return userFactory.createUser(registrationRequest);
    }
}
