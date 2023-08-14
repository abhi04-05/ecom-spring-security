package com.ty.ecom.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @Size(min = 5, max = 10, message = "username must be of 5 to 10 characters")
    @Pattern(regexp = "^[^@]*$", message = "username cannot contain @ character")
    private String username;
    @NotNull(message = "firstname cannot be null")
    @NotBlank(message = "firstname cannot be blank")
    private String firstName;
    private String middleName;
    @NotNull(message = "lastname cannot be null")
    @NotBlank(message = "lastname cannot be blank")
    private String lastName;
    @Email(message = "Please provide a valid email address")
    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotNull(message = "mobile number cannot be null")
    @Min(value = 1000000000, message = "mobile number must be of 10 digits")
    @Max(value = 9999999999L, message = "mobile number must be of 10 digits")
    private Long mobile;
    @Past(message = "dateOfBirth must be a past date")
    @NotNull(message = "dateOfBirth cannot be null")
    private Date dateOfBirth;
    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    @Size(min = 6, max = 15, message = "password must be of 6 to 15 characters")
    private String password;

}
