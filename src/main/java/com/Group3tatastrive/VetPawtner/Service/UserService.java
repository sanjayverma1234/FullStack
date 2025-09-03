package com.Group3tatastrive.VetPawtner.Service;

import com.Group3tatastrive.VetPawtner.Entity.User;
import com.Group3tatastrive.VetPawtner.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Insert a new user (for registration)
    public User registerUser(User user) {
        // Validate that passwords match (assuming Confirm Password is handled in the frontend)
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        // Set default role to USER
        user.setRole(User.Role.user);
        // Save user to database (password hashing should be added in a real application)
        return userRepository.save(user);
    }

    // Update existing user
    public User updateUser(Integer id, User user) {
        User user1 = userRepository.findById(id).orElse(null);
        if (user1 != null) {
            user1.setUsername(user.getUsername());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setUserphone(user.getUserphone());
            user1.setUseraddress(user.getUseraddress());
            user1.setRole(User.Role.user);
            return userRepository.save(user1);
        }
        return null;
    }

    // Delete user
    public User deleteUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }
}