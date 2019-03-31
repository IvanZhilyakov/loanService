package com.testTask.loanService.repository;

import com.testTask.loanService.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long>{
   Customer findByNameAndSurnameAndId(String name, String surname, Long id);
}
