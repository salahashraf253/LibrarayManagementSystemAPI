package com.example.libraraymangementsystemapi.config.rateLimit;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class RateLimiter {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Long>> requestMap = new ConcurrentHashMap<>();

    public boolean tryAcquire(String key, int requests, int seconds) {
        long currentTime = System.currentTimeMillis();
        long windowStart = currentTime - (seconds * 1000);

        // Get or create a queue for the given key
        requestMap.putIfAbsent(key, new ConcurrentLinkedQueue<>());
        ConcurrentLinkedQueue<Long> timestamps = requestMap.get(key);

        // Remove expired timestamps
        while (!timestamps.isEmpty() && timestamps.peek() < windowStart) {
            timestamps.poll();
        }

        // Check if the request limit is exceeded
        if (timestamps.size() < requests) {
            timestamps.add(currentTime);
            return true;
        }
        return false;
    }
}
