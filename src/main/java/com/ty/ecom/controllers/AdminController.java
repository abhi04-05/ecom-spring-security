package com.ty.ecom.controllers;

import com.ty.ecom.pojos.EcomResponse;
import com.ty.ecom.services.AdminService;
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
@RequestMapping("api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/get-admin")
    public ResponseEntity<EcomResponse> getAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body(EcomResponse.builder().isError(Boolean.FALSE).message("Admin controller works").build());
    }

}
