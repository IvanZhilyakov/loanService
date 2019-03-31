package com.testTask.loanService.exceptions;

public class CustomerNotFoundException extends LoanServiceException {
    public CustomerNotFoundException(String customerName, String customerSurname, Long customerId) {
        super(String.format("Customer %s %s with id=%s is not found", customerName, customerSurname, customerId));
    }

    public CustomerNotFoundException(Long customerId) {
        super(String.format("Customer with id=%s is not found",  customerId));
    }
}
