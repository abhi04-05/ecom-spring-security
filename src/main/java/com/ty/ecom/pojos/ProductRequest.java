package com.ty.ecom.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotNull(message = "productName cannot be null")
    @NotBlank(message = "productName cannot be blank")
    private String productName;
    @NotNull(message = "productPrice cannot be null")
    @Positive(message = "productPrice must be greater than zero")
    private Double productPrice;

}
