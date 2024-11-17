package com.scm.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scm.entities.Contact;
import com.scm.entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {

    // find contact by user
    Page<Contact> findByUser(User user, PageRequest pageable);

    // find contact by userId
    @Query("select c from Contact c where c.user.userId = :userId")
    List<Contact> findByUserId(String userId);

}
