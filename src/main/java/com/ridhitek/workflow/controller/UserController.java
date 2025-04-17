package com.ridhitek.workflow.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ridhitek.workflow.entity.User;
import com.ridhitek.workflow.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    public UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping("/create")
    public User saveUser(User user) throws DataIntegrityViolationException, Exception {
        return userService.createUser(user);
    }

    @PatchMapping("/update/{id}")
    public Optional<User> updateUser(@PathVariable UUID id, User user) {
        return Optional.ofNullable(userService.updateUser(id, user));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable UUID id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

}
