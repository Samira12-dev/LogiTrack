package com.example.LOGITRACK.dto.response;

import com.example.LOGITRACK.enumm.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDTO {
    private Long id;
    private String message;
    private NotificationType type;
    private LocalDateTime dateCreation;
    private Boolean read;
    private Long orderId;
}
