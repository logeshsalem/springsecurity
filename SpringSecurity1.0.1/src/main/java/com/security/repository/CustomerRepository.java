package com.security.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.security.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

	Optional<Customer> findByEmail(String email);
}
