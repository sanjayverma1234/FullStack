package com.Group3tatastrive.VetPawtner.Controller;

import com.Group3tatastrive.VetPawtner.Entity.User;
import com.Group3tatastrive.VetPawtner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping("/fetching")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Register a new user (mapped to the sign-up form)
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Validate input (basic check; enhance as needed)
            if (user.getUsername() == null || user.getUsername().isEmpty() ||
                    user.getEmail() == null || user.getEmail().isEmpty() ||
                    user.getPassword() == null || user.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "All fields are required"));
            }
            User savedUser = userService.registerUser(user);
            return ResponseEntity.ok(Map.of("message", "User registered successfully", "user", savedUser));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Registration failed: " + e.getMessage()));
        }
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Placeholder logic: In a real app, authenticate against the database
        User existingUser = userService.getAllUsers().stream()
                .filter(u -> u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword()))
                .findFirst()
                .orElse(null);
        if (existingUser != null) {
            return ResponseEntity.ok(Map.of("message", "Login successful", "user", existingUser));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Invalid email or password"));
    }

    // Insert a new user (alternative to register, if needed)


    // Update user
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(Map.of("message", "User updated successfully", "user", updatedUser));
        }
        return ResponseEntity.notFound().build();
    }

    // Delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}