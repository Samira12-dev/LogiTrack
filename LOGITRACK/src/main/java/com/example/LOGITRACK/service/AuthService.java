package com.example.LOGITRACK.service;

import com.example.LOGITRACK.config.JwtUtils;
import com.example.LOGITRACK.dto.request.LoginRequestDTO;
import com.example.LOGITRACK.dto.request.RegisterRequestDTO;
import com.example.LOGITRACK.dto.response.AuthResponse;
import com.example.LOGITRACK.entity.User;
import com.example.LOGITRACK.enumm.Role;
import com.example.LOGITRACK.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepo userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;




    public AuthResponse register(RegisterRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(request.getRole());

        User savedUser = userRepository.save(user);

        String token = jwtUtils.generateToken(
                savedUser.getEmail(),
                savedUser.getRole().name()
        );

        return new AuthResponse(
                token,
                savedUser.getId(),
                savedUser.getNom(),
                savedUser.getPrenom(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }






    public AuthResponse login(LoginRequestDTO request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );


        String token =
                jwtUtils.generateToken(
                        user.getEmail(),
                        user.getRole().name()
                );


        return new AuthResponse(
                token,
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getRole()
        );
    }

}