package com.example.libraraymangementsystemapi.dto.response;

import com.example.libraraymangementsystemapi.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}