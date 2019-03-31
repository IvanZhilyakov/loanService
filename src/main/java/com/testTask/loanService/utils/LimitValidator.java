package com.testTask.loanService.utils;

import com.testTask.loanService.exceptions.LimitExceededException;

public interface LimitValidator {
    void validateLimit(String countryCode) throws LimitExceededException;
}
