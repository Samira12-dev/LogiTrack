package com.example.LOGITRACK.client;

import com.example.LOGITRACK.dto.request.NotificationRequestDTO;
import com.example.LOGITRACK.dto.response.NotificationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

    @FeignClient(
            name = "notification-service",
            url = "${notification.service.url}"
    )
    public interface NotificationClient {

        @PostMapping("/api/notifications")
        NotificationResponseDTO createNotification(
                @RequestBody NotificationRequestDTO request
        );

}
