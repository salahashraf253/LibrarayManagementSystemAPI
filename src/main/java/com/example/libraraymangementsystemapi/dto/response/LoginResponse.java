package com.example.libraraymangementsystemapi.dto.response;

import com.example.libraraymangementsystemapi.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Role role;
}