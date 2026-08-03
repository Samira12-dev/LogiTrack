package com.example.LOGITRACK.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneCommandRequestDTO {

    @NotNull(message = "La quantité est obligatoire.")
    @Min(value = 1, message = "La quantité doit être supérieure à 0.")
    private Integer quantity;

    @NotNull(message = "Le produit est obligatoire.")
    private Long produitId;

    @NotNull(message = "La commande est obligatoire.")
    private Long commandeId;
}