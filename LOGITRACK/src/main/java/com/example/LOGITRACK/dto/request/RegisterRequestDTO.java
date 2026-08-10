package com.example.LOGITRACK.dto.request;

import com.example.LOGITRACK.enumm.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {

    @NotBlank(message = "Nom obligatoire")
    private String nom;

    @NotBlank(message = "Prenom obligatoire")
    private String prenom;

    @NotBlank(message = "Email obligatoire")
    @Email(message = "Email invalide")
    private String email;

    @NotNull(message = "Le rôle est obligatoire")
    private Role role;
    @NotBlank(message = "Password obligatoire")
    private String password;
}