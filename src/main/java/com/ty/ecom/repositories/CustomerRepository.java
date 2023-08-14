package com.ty.ecom.repositories;

import com.ty.ecom.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCustomerId(Integer customerId);
    Optional<Customer> findByCustomerUserName(String username);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByMobileNumber(Long mobile);
    Boolean existsByCustomerUserName(String username);
    Boolean existsByEmail(String email);
    Boolean existsByMobileNumber(Long mobile);

}
