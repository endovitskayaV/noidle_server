package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.NotificationDto;
import ru.vsu.noidle_server.model.dto.StatisticsDto;
import ru.vsu.noidle_server.service.NotificationService;
import ru.vsu.noidle_server.service.StatisticsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RedirectView save(@RequestBody List<StatisticsDto> statistics, @RequestParam("userId") UUID userId) {
        try {
            statisticsService.save(statistics, userId);
        } catch (ServiceException e) {
            return new RedirectView("/error");
        }
        return new RedirectView("/notifications?userId="+userId);
    }

    //to check plugin interaction with server
    @GetMapping(value = "/check")
    public ResponseEntity save() {
        return ResponseEntity.ok().build();
    }
}
