package com.example.LOGITRACK.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitRequestDTO {

    @NotBlank(message = "Le nom est obligatoire.")
    private String nom;

    @NotBlank(message = "La catégorie est obligatoire.")
    private String category;

    @NotNull(message = "Le prix est obligatoire.")
    @Min(value = 0, message = "Le prix doit être positif.")
    private Double price;

    @NotNull(message = "La quantité est obligatoire.")
    @Min(value = 0, message = "La quantité doit être positive.")
    private Integer quantityStock;
}