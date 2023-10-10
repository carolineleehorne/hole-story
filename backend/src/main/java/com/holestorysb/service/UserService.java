package com.holestorysb.service;

import com.holestorysb.model.UserRole;
import com.holestorysb.repository.UserRepository;
import com.holestorysb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId()).orElse(null);
        if (existingUser != null) {
            if (user.getUsername() != null) {
                User userWithSameUsername = userRepository.findByUsername(user.getUsername());
                if (userWithSameUsername != null && !userWithSameUsername.getUserId().equals(user.getUserId())) {
                    // Another user with the same username exists
                    return null;
                }
                existingUser.setUsername(user.getUsername());
            }
            // Add more fields here if needed (like profile picture, etc.)

            return userRepository.save(existingUser);
        }
        return null;
    }


//    public User createAdmin(User user) {                                     // This was for testing admin authorizations
//        user.setRole(UserRole.ADMIN);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Any additional methods you have...
}