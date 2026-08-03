package com.example.LOGITRACK.controller;

import com.example.LOGITRACK.dto.request.LoginRequestDTO;
import com.example.LOGITRACK.dto.request.RegisterRequestDTO;
import com.example.LOGITRACK.dto.response.AuthResponse;
import com.example.LOGITRACK.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;



    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequestDTO request
    ){

        return ResponseEntity.ok(
                authService.register(request)
        );
    }



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequestDTO request
    ){

        return ResponseEntity.ok(
                authService.login(request)
        );
    }

}