package com.testTask.loanService.service;

import com.testTask.loanService.entities.Customer;
import com.testTask.loanService.entities.Loan;
import com.testTask.loanService.exceptions.CustomerInBlackListException;
import com.testTask.loanService.exceptions.CustomerNotFoundException;
import com.testTask.loanService.exceptions.LoanServiceException;
import com.testTask.loanService.repository.CustomerRepository;
import com.testTask.loanService.repository.LoanRepository;
import com.testTask.loanService.utils.DetermineCountryService;
import com.testTask.loanService.utils.LimitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LimitValidator limitValidator;

    @Autowired
    DetermineCountryService determineCountryService;

    @Override
    public Long applyForLoan(Double amount, Integer term, String customerName, String customerSurname, Long customerId, String ip) throws LoanServiceException {
        String country = determineCountryService.determineCountry(ip);
        limitValidator.validateLimit(country);

        Customer customer = customerRepository.findByNameAndSurnameAndId(customerName, customerSurname, customerId);
        if (customer == null) {
            throw new CustomerNotFoundException(customerName, customerSurname, customerId);
        }

        validateCustomer(customer);

        Loan loan =new Loan(term, amount, customer, country);
        loanRepository.save(loan);
        System.out.println(loan.toString());
        return loan.getId();
    }

    @Override
    public List<Loan> getAllApprovedLoans() {
        return loanRepository.findAll();
    }

    @Override
    public List<Loan> getAllApprovedLoansByCustomerId(Long customerId) throws LoanServiceException {
        Customer customer = null;
        try {
            customer = customerRepository.findById(customerId).get();
        } catch (java.util.NoSuchElementException e) {
            throw new CustomerNotFoundException(customerId);
        }

        return loanRepository.findAllByCustomer(customer);
    }


    private void validateCustomer(@NotNull Customer customer) throws LoanServiceException {
        if (customer.getInBlackList() == true) {
            throw new CustomerInBlackListException(customer.getName(), customer.getSurname(), customer.getId());
        }
    }

}
