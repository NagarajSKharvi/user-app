package com.apexon.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequest {

//    @Pattern(regexp = "^(?!\\s*$).+", message = "Description must not be blank, empty, or contain only whitespace")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 50, message = "Email must not exceed 50 characters")
    private String username;
    @Size(min = 8, message = "Password should contain at least 8 characters")
    @Size(max = 50, message = "Password must not exceed 50 characters")
    private String password;
    @NotBlank(message = "First Name must not be null, blank, or contain only whitespace")
    @Size(max = 50, message = "First Name must not exceed 50 characters")
    private String firstName;
    @NotBlank(message = "Last Name must not be null, blank, or contain only whitespace")
    @Size(max = 50, message = "Last Name must not exceed 50 characters")
    private String lastName;
    @Size(max = 10, message = "Contact Number must not exceed 10 characters")
    private String contact;
}
