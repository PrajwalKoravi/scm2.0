package com.scm.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid Email Address   [ ex: john@gmail.com ]")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min=10, max=10, message="Invalid Phone Number")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;


    private String description;

    private Boolean favorite;

    private String websiteLink;

    private String linkedInLink;


    // Size 
    // Resolution
    private MultipartFile contactImage;
}
