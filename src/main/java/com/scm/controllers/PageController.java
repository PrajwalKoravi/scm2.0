package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class PageController {

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
}
