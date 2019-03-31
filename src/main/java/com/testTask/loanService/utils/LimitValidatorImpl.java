package com.testTask.loanService.utils;

import com.google.common.util.concurrent.RateLimiter;
import com.testTask.loanService.exceptions.LimitExceededException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Component
public class LimitValidatorImpl implements LimitValidator {
    @Value("${loanApplicationsPerSec}")
    private Integer MAX_APPLICATIONS_PER_SECOND;

    private Map<String, RateLimiter> limitsMap = new ConcurrentHashMap<>();
    int i=0;

    @Override
    public void validateLimit(String countryCode) throws LimitExceededException {
        RateLimiter rateLimiter = null;
        rateLimiter = limitsMap.get(countryCode);
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(MAX_APPLICATIONS_PER_SECOND);
            limitsMap.put(countryCode, rateLimiter);
        }

        rateLimiter.tryAcquire(1);
//        if (!rateLimiter.tryAcquire(1, 1000, MICROSECONDS)){
//            throw new LimitExceededException(countryCode);
//        }

    }
}
