package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.entities.User;

public interface UsersRepo extends JpaRepository<User, String>{

    Optional<User> findByEmail(String email);

}
