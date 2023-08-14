package com.ty.ecom.services;

import com.ty.ecom.models.Customer;
import com.ty.ecom.repositories.CustomerRepository;
import com.ty.ecom.utility.EcomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EcomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String userDetails) throws UsernameNotFoundException {

        if (userDetails.contains(Character.toString('@'))) {
            Optional<Customer> customerEmail = customerRepository.findByEmail(userDetails);
            if (customerEmail.isPresent()) {
                if (Boolean.TRUE.equals(customerEmail.get().getIsActive())) {
                    return new EcomUserDetails(customerEmail.get());
                }
                throw new DisabledException("User is currently disabled !!");
            }
            throw new UsernameNotFoundException("User not found with: " + userDetails);
        }

        Optional<Customer> customerUserName = customerRepository.findByCustomerUserName(userDetails);
        if (customerUserName.isPresent()) {
            if(Boolean.TRUE.equals(customerUserName.get().getIsActive())) {
                return new EcomUserDetails(customerUserName.get());
            }
            throw new DisabledException("User is currently disabled !!");
        }
            throw new UsernameNotFoundException("User not found with: " + userDetails);
    }
}
