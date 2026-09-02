package com.example.LOGITRACK.dto.request;

import com.example.LOGITRACK.enumm.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDTO {
    private String message;

    private NotificationType type;

    private Long orderId;
}
