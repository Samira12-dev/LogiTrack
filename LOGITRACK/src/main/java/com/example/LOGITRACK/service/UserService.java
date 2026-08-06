package com.example.LOGITRACK.service;

import com.example.LOGITRACK.entity.User;
import com.example.LOGITRACK.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
