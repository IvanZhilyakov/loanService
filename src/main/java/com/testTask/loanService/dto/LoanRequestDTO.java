package com.testTask.loanService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestDTO {

    private Double loanAmount;
    /**
     * term is how many days need for loan
     */
    private Integer term;
    private String name;
    private String surname;
    private Long personalId;


}
