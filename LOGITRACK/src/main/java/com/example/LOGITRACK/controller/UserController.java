package com.example.LOGITRACK.controller;

import com.example.LOGITRACK.entity.User;
import com.example.LOGITRACK.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/me")
    public User getMyProfile(Authentication authentication) {
        return userService.getMyProfile(authentication.getName());
    }
}
