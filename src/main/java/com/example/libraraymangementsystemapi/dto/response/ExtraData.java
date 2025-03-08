package com.example.libraraymangementsystemapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtraData {
    private String timeStamp;
    private int responseSize;
    private String requestId;
    private String serverInfo;
    private String clientIp;
    private String apiVersion;
    private String userAgent;
}
