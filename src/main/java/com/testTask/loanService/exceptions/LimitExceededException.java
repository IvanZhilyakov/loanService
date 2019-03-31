package com.testTask.loanService.exceptions;

public class LimitExceededException extends LoanServiceException {
    public LimitExceededException(String region) {
        super(String.format("Limit of applications from region %s exceeded", region));
    }
}
