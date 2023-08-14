package com.ty.ecom.services;

import com.ty.ecom.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final CustomerRepository customerRepository;

}
