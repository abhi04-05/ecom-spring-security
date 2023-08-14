package com.ty.ecom.services;

import com.ty.ecom.exceptions.SignUpException;
import com.ty.ecom.models.Customer;
import com.ty.ecom.models.Role;
import com.ty.ecom.pojos.EcomResponse;
import com.ty.ecom.pojos.EmailLoginRequest;
import com.ty.ecom.pojos.SignupRequest;
import com.ty.ecom.pojos.UsernameLoginRequest;
import com.ty.ecom.repositories.CustomerRepository;
import com.ty.ecom.repositories.RoleRepository;
import com.ty.ecom.utility.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final EcomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public EcomResponse loginByUsername(UsernameLoginRequest usernameLoginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameLoginRequest.getUsername(), usernameLoginRequest.getPassword()));
        }catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Incorrect credentials !!");
        }catch (LockedException exception) {
            throw new LockedException("User account is locked !!");
        }catch (DisabledException exception) {
            throw new DisabledException("User is disabled currently !!");
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(usernameLoginRequest.getUsername());
        return EcomResponse.builder().isError(Boolean.FALSE).message("Token generated successfully")
                .data(jwtUtils.generateToken(userDetails)).build();
    }

    public EcomResponse loginByEmail(EmailLoginRequest emailLoginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(emailLoginRequest.getEmail(), emailLoginRequest.getPassword()));
        }catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Incorrect credentials !!");
        }catch (LockedException exception) {
            throw new LockedException("User account is locked !!");
        }catch (DisabledException exception) {
            throw new DisabledException("User is disabled currently !!");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(emailLoginRequest.getEmail());
        return EcomResponse.builder().isError(Boolean.FALSE).message("Token generated successfully")
                .data(jwtUtils.generateToken(userDetails)).build();
    }

    public EcomResponse userSignUp(SignupRequest signupRequest) {

        if(Boolean.TRUE.equals(roleRepository.existsByRoleName("admin"))) {

            if(Boolean.TRUE.equals(customerRepository.existsByCustomerUserName(signupRequest.getUsername())
                    || customerRepository.existsByEmail(signupRequest.getEmail()) || customerRepository.existsByMobileNumber(signupRequest.getMobile()))) {
                throw new SignUpException("User already exists");
            }

            Role role = roleRepository.findByRoleName("user").orElse(Role.builder().roleName("user").build());

            Customer customer = Customer.builder().customerUserName(signupRequest.getUsername()).firstName(signupRequest.getFirstName())
                    .middleName(signupRequest.getMiddleName()).lastName(signupRequest.getLastName()).email(signupRequest.getEmail())
                    .mobileNumber(signupRequest.getMobile()).dateOfBirth(signupRequest.getDateOfBirth()).password(passwordEncoder.encode(signupRequest.getPassword()))
                    .role(role).isActive(Boolean.TRUE).isLocked(Boolean.FALSE).timestamp(LocalDateTime.now()).build();

            customerRepository.save(customer);
            return EcomResponse.builder().isError(Boolean.FALSE).message("Sign up successful").build();
        }
        throw new SignUpException("SignUp not successful, Please contact admin");
    }

    public EcomResponse adminSignUp(SignupRequest signupRequest) {

        if(customerRepository.findAll().isEmpty()) {

            Role role = Role.builder().roleName("admin").build();
            Customer customer = Customer.builder().customerUserName(signupRequest.getUsername()).firstName(signupRequest.getFirstName())
                    .middleName(signupRequest.getMiddleName()).lastName(signupRequest.getLastName()).email(signupRequest.getEmail())
                    .mobileNumber(signupRequest.getMobile()).dateOfBirth(signupRequest.getDateOfBirth()).password(passwordEncoder.encode(signupRequest.getPassword()))
                    .role(role).isActive(Boolean.TRUE).isLocked(Boolean.FALSE).timestamp(LocalDateTime.now()).build();

            customerRepository.save(customer);
            return EcomResponse.builder().isError(Boolean.FALSE).message("Admin Sign up successful").build();
        }
        throw new SignUpException("Admin already registered !!");
    }

}
