package com.scm.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    // add contact page : Handler
    @GetMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }


    @PostMapping("/add")
    public String saveContact(@Valid ContactForm contactForm, BindingResult result, Authentication authentication, HttpSession session) {

        // process form data

        // Validation
        if (result.hasErrors()) {
            // send error message
            Message message = Message.builder().content("Please correct the validation errors").type(MessageType.red).build();
            session.setAttribute("message", message);
            return "user/add_contact";
        }


        // get logged in user
        String userName = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(userName);

        // Process contact picture
        logger.info("file information : {}", contactForm.getContactImage().getOriginalFilename());

        String fileURL = imageService.uploadImage(contactForm.getContactImage());

        // convert contactForm data to Contact entity
        Contact contact = new Contact();
        
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setFavorite(contactForm.getFavorite());
        contact.setPicture(fileURL);

        contact.setUser(user);

        //contactService.saveContact(contact);

        // Set the contact image url
        System.out.println(contactForm);

        // Set the success message
        Message message = Message.builder().content("You have added a new contact successfully").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "redirect:/user/contacts/add";
    }
}
