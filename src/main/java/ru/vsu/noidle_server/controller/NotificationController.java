package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.noidle_server.model.dto.NotificationDto;
import ru.vsu.noidle_server.service.NotificationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private NotificationService notificationService;

    //will be shown to the user
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NotificationDto>> getNotifications(@RequestParam("userId") UUID userId) {
        List<NotificationDto> notifications = notificationService.getAll(userId);
        if (notifications == null) {
            //user not found
            return ResponseEntity.notFound().build();
        }
        return notifications.isEmpty() ?
                ResponseEntity.noContent().build() :
                new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}
