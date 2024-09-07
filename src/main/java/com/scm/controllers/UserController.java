package com.scm.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.helpers.Helper;
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
    public String userProfile(Authentication authentication) {
    
        String username = Helper.getEmailOfLoggedInUser(authentication);

        logger.info("User logged in: {}", username);

        // Fetch data from database
        
        User user = UserService.getUserByEmail(username);

        System.out.println(user.getName());
        System.out.println(user.getEmail());
        return "user/profile";
    }

    // user add contact page

    // user view page

    // user edit contact

    // user delete contact

    // user search contact


}
