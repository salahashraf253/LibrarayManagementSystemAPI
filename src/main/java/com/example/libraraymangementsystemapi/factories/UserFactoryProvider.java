package com.example.libraraymangementsystemapi.factories;

import com.example.libraraymangementsystemapi.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserFactoryProvider {
    private AdminFactory adminFactory;
    private BorrowerFactory borrowerFactory;
    public UserFactory getUserFactory(Role role) {
        switch (role) {
            case ADMIN:
                return adminFactory;
            case BORROWER:
                return borrowerFactory;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}