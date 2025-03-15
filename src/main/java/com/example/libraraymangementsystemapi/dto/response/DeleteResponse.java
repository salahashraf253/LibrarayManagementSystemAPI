package com.example.libraraymangementsystemapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteResponse {
    private int statusCode;
    private String message;
}
