package com.ty.ecom.controllers;

import com.ty.ecom.pojos.EcomResponse;
import com.ty.ecom.services.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "ecom-api")
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get-customer")
    public ResponseEntity<EcomResponse> getCustomer() {
        return ResponseEntity.status(HttpStatus.OK).body(EcomResponse.builder().isError(Boolean.FALSE).message("Customer controller works").build());
    }

}
