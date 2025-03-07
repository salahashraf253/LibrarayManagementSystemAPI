package com.example.libraraymangementsystemapi.dto.request;

import com.example.libraraymangementsystemapi.enums.Role;
import lombok.Data;

@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}