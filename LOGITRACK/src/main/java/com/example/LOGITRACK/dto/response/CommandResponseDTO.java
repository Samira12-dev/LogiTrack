package com.example.LOGITRACK.dto.response;

import com.example.LOGITRACK.enumm.CommandeStatut;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandResponseDTO {

    private Long id;
    private LocalDate datecommand;
    private CommandeStatut commandeStatut;
    private Long clientId;
}