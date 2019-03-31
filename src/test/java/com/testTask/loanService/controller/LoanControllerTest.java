package com.testTask.loanService.controller;

import com.testTask.loanService.LoanServiceApplication;
import com.testTask.loanService.entities.Customer;
import com.testTask.loanService.repository.CustomerRepository;
import com.testTask.loanService.repository.LoanRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoanServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoanControllerTest {
    private static final String APPLY_LOAN_ENDPOINT = "/loans/applyLoan";
    private static final String ALL_APPROVED_LOANS_ENDPOINT = "/loans/allApprovedLoans";
    private static final String ALL_APPROVED_LOANS_BY_ID_ENDPOINT = "/loans/approvedLoansByUserId"; //todo вынести в отдельный класс
    private static final String APPLICATION_JSON = "application/json";

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LoanRepository loanRepository;

    private Customer customerGood = new Customer("Ivan", "Ivanov");
    private Customer customerInBlackList = new Customer("Petr", "Petrov");

    @Before
    public void setup() {

        customerInBlackList.setInBlackList(true);
        customerRepository.save(customerGood);
        customerRepository.save(customerInBlackList);

    }

    @Test
    public void applyLoanWithGoodCustomer() {
        given()
                .param("loanAmount", String.valueOf(1000.00))
                .param("term", String.valueOf(15))
                .param("name", customerGood.getName())
                .param("surname", customerGood.getSurname())
                .param("personalId", String.valueOf(customerGood.getId()))
                .when().post(APPLY_LOAN_ENDPOINT).then()
                .contentType(APPLICATION_JSON)
                .statusCode(200);
    }

    @Test
    public void applyLoanWithBannedCustomer() {
        given()
                .param("loanAmount", String.valueOf(1000.00))
                .param("term", String.valueOf(15))
                .param("name", customerInBlackList.getName())
                .param("surname", customerInBlackList.getSurname())
                .param("personalId", String.valueOf(customerInBlackList.getId()))
                .when().post(APPLY_LOAN_ENDPOINT).then()
                .contentType(APPLICATION_JSON)
                .statusCode(500);
    }

    @Test
    public void getAllApprovedLoans() {

       given().when().
               get(ALL_APPROVED_LOANS_ENDPOINT).then()
               .contentType(APPLICATION_JSON)
                .statusCode(200);
    }

    @Test
    public void getAllApprovedLoansByUserId() {
        given()
                .param("customerId", customerGood.getId())
                .when().get(ALL_APPROVED_LOANS_BY_ID_ENDPOINT).then()
                .contentType(APPLICATION_JSON)
                .statusCode(200);

    }

}