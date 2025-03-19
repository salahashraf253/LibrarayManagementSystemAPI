package com.example.libraraymangementsystemapi.config.rateLimit;

import com.example.libraraymangementsystemapi.exception.RateLimitExceededException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@AllArgsConstructor
public class RateLimitAspect {
    private final RateLimitConfig rateLimitConfig;
    private final RateLimiter rateLimiter;

    @Around("@annotation(RateLimited)")
    public Object enforceRateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new IllegalStateException("No request context found");
        }

        HttpServletRequest request = requestAttributes.getRequest();
        String key = getKey(request);

        if (!rateLimiter.tryAcquire(key, rateLimitConfig.getRequests(), rateLimitConfig.getSeconds())) {
            throw new RateLimitExceededException("Rate limit exceeded");
        }

        return joinPoint.proceed();
    }

    private String getKey(HttpServletRequest request) {
        return request.getRemoteAddr(); // Using IP address as the key
    }
}
