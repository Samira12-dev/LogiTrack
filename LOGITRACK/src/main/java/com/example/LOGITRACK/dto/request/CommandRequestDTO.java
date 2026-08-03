package com.example.LOGITRACK.dto.request;

import com.example.LOGITRACK.enumm.CommandeStatut;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandRequestDTO {

    @NotNull(message = "La date de commande est obligatoire.")
    private LocalDate datecommand;

    @NotNull(message = "Le statut est obligatoire.")
    private CommandeStatut commandeStatut;

    @NotNull(message = "Le client est obligatoire.")
    private Long clientId;

}