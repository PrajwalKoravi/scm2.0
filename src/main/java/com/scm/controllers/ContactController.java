package com.scm.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.AppConstants;
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
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, Authentication authentication, HttpSession session) {

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

        String filename = UUID.randomUUID().toString();

        String fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);

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
        contact.setCloudinaryImagePublicId(filename);

        contact.setUser(user);

        contactService.saveContact(contact);

        // Set the contact image url
        System.out.println(contactForm);

        // Set the success message
        Message message = Message.builder().content("You have added a new contact successfully").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "redirect:/user/contacts/add";
    }

    // view contacts

    @GetMapping
    public String viewContacts(
     @RequestParam(value = "page", defaultValue= "0") int page,
     @RequestParam(value = "size", defaultValue = "10") int size,
     @RequestParam(value = "sort", defaultValue = "name") String sortBy,
     @RequestParam(value = "direction", defaultValue = "asc") String direction   
    ,Model model,Authentication authentication) {

        //load all the user contacts

        String userName = Helper.getEmailOfLoggedInUser(authentication);

        User user =userService.getUserByEmail(userName);

        Page<Contact> pageContacts = contactService.getByUser(user, page, size, sortBy, direction);

       

        model.addAttribute("pageContacts", pageContacts);
        model.addAttribute("pageSize", AppConstants.Page_Size);

       return "user/contacts"; 
    }
}
