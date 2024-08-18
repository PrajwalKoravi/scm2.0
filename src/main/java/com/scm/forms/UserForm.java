package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message="UserName is required")
    @Size(min=3, message="Minimum 3 charachters are required")
    private String name;

    @Email(message="Invalid Email Address")
    @NotBlank(message="Email is required")
    private String email;

    @NotBlank(message="Password is required")
    @Size(min=8, message="Minimum 8 charachters are required")
    private String password;

    @NotBlank(message="About is required")
    private String about;

    @Size(min=10, max=10, message="Invalid Phone Number")
    private String phoneNumber;      
}
