package com.holestorysb.repository;

import com.holestorysb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // This gives the basic CRUD operations for the User entity

    User findByUsername(String username);

}