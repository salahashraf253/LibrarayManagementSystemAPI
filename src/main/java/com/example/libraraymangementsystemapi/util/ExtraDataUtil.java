package com.example.libraraymangementsystemapi.util;

import com.example.libraraymangementsystemapi.config.AppInfoConfig;
import com.example.libraraymangementsystemapi.dto.response.ExtraData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
public class ExtraDataUtil {
    private final AppInfoConfig appInfoConfig;
    public ExtraData buildExtraData(HttpServletRequest request,int responseSize){
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
