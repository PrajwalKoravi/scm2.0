package com.scm.services;

import java.util.List;

import com.scm.entities.Contact;


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
    List<Contact> getContactByUser(String userId);
}
