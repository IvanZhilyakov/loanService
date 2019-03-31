package com.testTask.loanService.controller;

import com.testTask.loanService.dto.LoanRequestDTO;
import com.testTask.loanService.exceptions.LoanServiceException;
import com.testTask.loanService.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    LoanService loanService;

    @PostMapping(value = "/applyLoan", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity applyLoan(LoanRequestDTO loanRequestDTO, HttpServletRequest request) {
        try {
            Long loanId = loanService.applyForLoan(loanRequestDTO.getLoanAmount(),
                    loanRequestDTO.getTerm(),
                    loanRequestDTO.getName(),
                    loanRequestDTO.getSurname(),
                    loanRequestDTO.getPersonalId(),
                    request.getRemoteAddr());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Collections.singletonMap("loanId", loanId));

        } catch (LoanServiceException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping(value = "/allApprovedLoans", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllApprovedLoans() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanService.getAllApprovedLoans());
    }

    @GetMapping(value = "/approvedLoansByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllApprovedLoansByUserId(@RequestParam Long customerId) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(loanService.getAllApprovedLoansByCustomerId(customerId));
        } catch (LoanServiceException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping(value = "/addCustomer")
    public String addCustomer(@RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam Boolean inBlackList) {
        loanService.addCustomer(name, surname, inBlackList);
        return "customer successfully created";
    }

}
