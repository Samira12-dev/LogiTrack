package com.example.LOGITRACK.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneCommandResponseDTO {

    private Long id;
    private Integer quantity;
    private Long produitId;
    private Long commandeId;
}