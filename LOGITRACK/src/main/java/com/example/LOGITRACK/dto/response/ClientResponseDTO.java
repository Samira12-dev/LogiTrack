package com.example.LOGITRACK.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponseDTO {

    private Long id;

    private String nom;

    private String email;

    private String telephone;

    private String ville;
}