package com.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

	List<Customer> findByEmail(String email);
}
