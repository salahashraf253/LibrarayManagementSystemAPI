package com.example.libraraymangementsystemapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "app.info")
public class AppInfoConfig {
    private String serverInfo;
    private String apiVersion;
}
