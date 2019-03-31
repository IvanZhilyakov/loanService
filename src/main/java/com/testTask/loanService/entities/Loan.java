package com.testTask.loanService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer term;
    private Double amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String countryCode;

    @JsonIgnore
    @ManyToOne
    private Customer customer;

    public Loan(Integer term, Double amount, Customer customer, String countryCode) {
        this.term = term;
        this.amount = amount;
        this.customer = customer;
        this.countryCode = countryCode;

        startDate = LocalDate.now();
        endDate = startDate.plusDays(term);
    }

}
