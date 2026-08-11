package com.example.LOGITRACK.controller;

import com.example.LOGITRACK.entity.User;
import com.example.LOGITRACK.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size) {
        Pageable pageable= PageRequest.of(page,size);
        return userService.getAllUsers(pageable);
    }
    @GetMapping("/me")
    public User getMyProfile(Authentication authentication) {
        return userService.getMyProfile(authentication.getName());
    }
}
