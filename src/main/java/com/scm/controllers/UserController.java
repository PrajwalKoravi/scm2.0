package com.scm.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService UserService;
    

    // user dashboard page

    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    // user profile page

    @GetMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }

    // user add contact page

    // user view page

    // user edit contact

    // user delete contact

    // user search contact


}
