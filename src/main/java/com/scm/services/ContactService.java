package com.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scm.entities.Contact;
import com.scm.entities.User;


public interface ContactService {

    // save contact
    Contact saveContact(Contact contact);

    // get all contacts
    List<Contact> getAllContacts();

    // update contact
    Contact updateContact(Contact contact);

    // get contact by id
    Contact getContactById(String id);

    // delete contact
    void  deleteContact(String id);

    // search contact
    List<Contact> searchContact(String name, String email, String phoneNumber);

    // get contact by user
    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction); 
}
