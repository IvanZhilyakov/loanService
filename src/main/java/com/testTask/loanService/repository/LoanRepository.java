package com.testTask.loanService.repository;

import com.testTask.loanService.entities.Customer;
import com.testTask.loanService.entities.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findAll();
    List<Loan> findAllByCustomer(Customer customer);
}
