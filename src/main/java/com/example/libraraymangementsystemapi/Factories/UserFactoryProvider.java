package com.example.libraraymangementsystemapi.Factories;

import com.example.libraraymangementsystemapi.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFactoryProvider {
    @Autowired
    private AdminFactory adminFactory;
    @Autowired
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