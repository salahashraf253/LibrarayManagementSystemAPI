package com.example.libraraymangementsystemapi.util;

import com.example.libraraymangementsystemapi.config.AppInfoConfig;
import com.example.libraraymangementsystemapi.dto.response.ApiResponse;
import com.example.libraraymangementsystemapi.dto.response.ExtraData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
public class ExtraDataUtil {
    private final AppInfoConfig appInfoConfig;
    public <T> ResponseEntity<ApiResponse<T>> buildResponse(
            HttpServletRequest httpRequest,
            boolean includeExtraData,
            T data,
            HttpStatus status) {
        ExtraData extraData = null;
        if (includeExtraData) {
            extraData = buildExtraData(
                    httpRequest,
                    data.toString().getBytes().length
            );
        }
        ApiResponse<T> apiResponse = new ApiResponse<>(status.value(), data, extraData);
        return new ResponseEntity<>(apiResponse, status);
    }

    private ExtraData buildExtraData(HttpServletRequest request, int responseSize) {
        return new ExtraData(
                Instant.now().toString(),
                responseSize,
                request.getHeader("X-Request-ID"),
                appInfoConfig.getServerInfo(),
                request.getRemoteAddr(),
                appInfoConfig.getApiVersion(),
                request.getHeader("User-Agent")
        );
    }
}
