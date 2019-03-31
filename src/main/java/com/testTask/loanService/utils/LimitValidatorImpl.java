package com.testTask.loanService.utils;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LimitValidatorImpl implements LimitValidator {
    @Value("${loanApplicationsPerSec}")
    private Integer MAX_APPLICATIONS_PER_SECOND;

    private Map<String, RateLimiter> limitsMap = new ConcurrentHashMap<>();

    @Override
    public void validateLimit(String countryCode) {
        RateLimiter rateLimiter = null;
        rateLimiter = limitsMap.get(countryCode);
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(MAX_APPLICATIONS_PER_SECOND);
            limitsMap.put(countryCode, rateLimiter);
        }
        rateLimiter.acquire(1);
    }
}
