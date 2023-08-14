package com.ty.ecom.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailLoginRequest {

    @Email(message = "Please provide a valid email address")
    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    @Size(min = 6, max = 15, message = "password must be of 6 to 15 characters")
    private String password;

}
