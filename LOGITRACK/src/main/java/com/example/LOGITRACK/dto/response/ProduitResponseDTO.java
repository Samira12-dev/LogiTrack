package com.example.LOGITRACK.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitResponseDTO {

    private Long id;
    private String nom;
    private String category;
    private Double price;
    private Integer quantityStock;
}