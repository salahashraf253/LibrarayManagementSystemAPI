package com.example.libraraymangementsystemapi.mappers;

import com.example.libraraymangementsystemapi.factories.UserFactory;
import com.example.libraraymangementsystemapi.factories.UserFactoryProvider;
import com.example.libraraymangementsystemapi.dto.request.RegistrationRequest;
import com.example.libraraymangementsystemapi.dto.response.RegistrationResponse;
import com.example.libraraymangementsystemapi.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
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
