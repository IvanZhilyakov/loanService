package com.testTask.loanService.exceptions;

public class CustomerInBlackListException extends LoanServiceException {
    public CustomerInBlackListException(String customerName, String customerSurname, Long customerId) {
        super(String.format("Customer %s %s с id=%s in the black list", customerName, customerSurname, customerId));
    }
}
