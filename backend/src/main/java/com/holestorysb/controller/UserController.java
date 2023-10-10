package com.holestorysb.controller;

import com.holestorysb.model.User;
import com.holestorysb.model.UserRole;
import com.holestorysb.repository.UserRepository;
import com.holestorysb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User savedUser = userService.createUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/setup-account")
    public ResponseEntity<User> setupAccount(@RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(user);
            if(updatedUser != null) {
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                // Handle the scenario when the username is already taken
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/check-username")
    public ResponseEntity<?> checkUsernameAvailability(@RequestParam String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(false); // Username is taken
        } else {
            return ResponseEntity.ok(true); // Username is available
        }
    }



//    @PostMapping("/register-admin")                                          // This was for testing admin authorizations
//    public ResponseEntity<User> registerAdmin(@RequestBody User user) {
//        try {
//            User savedAdmin = userService.createAdmin(user);
//            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
//        } catch(Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }



    // Other user-related endpoints will go here in the future
}
