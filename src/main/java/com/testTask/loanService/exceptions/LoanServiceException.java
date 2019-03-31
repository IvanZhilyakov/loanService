package com.testTask.loanService.exceptions;

public class LoanServiceException extends Exception {
    public LoanServiceException(String message) {
        super(message);
    }

    public LoanServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoanServiceException(Throwable cause) {
        super(cause);
    }

    public LoanServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
