package com.ty.ecom.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsernameLoginRequest {

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @Size(min = 5, max = 10, message = "username must be of 5 to 10 characters")
    @Pattern(regexp = "^[^@]*$", message = "username cannot contain @ character")
    private String username;
    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    @Size(min = 6, max = 15, message = "password must be of 6 to 15 characters")
    private String password;

}
