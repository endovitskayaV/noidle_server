package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.AchievementType;
import ru.vsu.noidle_server.model.dto.NotificationDto;
import ru.vsu.noidle_server.service.NotificationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    //will be shown to the user
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NotificationDto>> getNotifications(
            @RequestParam("userId") UUID userId, @RequestParam(value = "types", required = false) List<AchievementType> types) {
        List<NotificationDto> notifications;
        try {
            notifications = notificationService.getAll(userId, types);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        if (notifications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}
