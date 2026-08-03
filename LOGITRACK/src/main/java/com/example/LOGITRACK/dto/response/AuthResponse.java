package com.example.LOGITRACK.dto.response;


import com.example.LOGITRACK.enumm.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;

    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private Role role;

}