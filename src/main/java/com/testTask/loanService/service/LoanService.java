package com.testTask.loanService.service;

import com.testTask.loanService.entities.Loan;
import com.testTask.loanService.exceptions.LoanServiceException;

import java.util.List;

public interface LoanService {

    Long applyForLoan(Double amount, Integer term, String customerName, String customerSurname, Long customerId, String ip) throws LoanServiceException;

    List<Loan> getAllApprovedLoans();

    List<Loan> getAllApprovedLoansByCustomerId(Long customerId) throws LoanServiceException;
}
