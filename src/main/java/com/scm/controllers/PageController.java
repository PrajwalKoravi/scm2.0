package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;




@Controller
public class PageController {

    @Autowired
    private UserService userService;

    // home page

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        model.addAttribute("name", "substring");
        model.addAttribute("Process", "Join");
        return "home";
    }


    //about routing

    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println("About Page Loading...");
        return "about";
    }

    // Services

    @RequestMapping("/services")
    public String services() {
        System.out.println("Services Page Loading...");
        return "services";
    }

    // contact

    @GetMapping("/contact") 
    public String contact() {
        System.out.println("Contact Page Loading...");
        return "contact";
    }

    // login

    @GetMapping("/login") 
    public String login() {
        System.out.println("Login Page Loading...");
        return "login";
    }

    // signup

    @GetMapping("/register") 
    public String register(Model model) {
        
        UserForm userForm  = new UserForm();
        // default data can be addded...
        model.addAttribute("userForm", userForm);
        System.out.println("Signup Page Loading...");
        return "register";
    }

    // process register

    @RequestMapping(value = "/do-register", method= RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
        System.out.println("Process resgistration");
        // fetch formdata
        // Userform
        System.out.println(userForm);
        // validate form data
        if(rBindingResult.hasErrors()) {
            return "register";
        }

        // save to database

        //user service
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://img.freepik.com/free-vector/user-circles-set_78370-4704.jpg?t=st=1723784531~exp=1723788131~hmac=b3003d0719a6d745e920e8e4253cbe58e2a02de234db10616fbb7947569ee0aa&w=826")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://img.freepik.com/free-vector/user-circles-set_78370-4704.jpg?t=st=1723784531~exp=1723788131~hmac=b3003d0719a6d745e920e8e4253cbe58e2a02de234db10616fbb7947569ee0aa&w=826");
        
        User savedUser = userService.saveUser(user);
        System.out.println("user saved");

        // message "Succcessful"
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);

        // redirect to page that you want
        return "redirect:/register";
    }
}   
