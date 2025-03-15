package com.example.libraraymangementsystemapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T>{
    private int statusCode;
    private T data;
    private ExtraData extraData;

    public ApiResponse(int statusCode,T data){
        this.statusCode=statusCode;
        this.data=data;
    }
}
