package com.example.libraraymangementsystemapi.config.rateLimit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class RateLimitConfig {
    @Value("${rate.limit.requests}")
    private int requests;

    @Value("${rate.limit.seconds}")
    private int seconds;
}
