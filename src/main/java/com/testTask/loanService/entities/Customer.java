package com.testTask.loanService.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String surname;

    @Getter
    @Setter
    private Boolean inBlackList=false;

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
