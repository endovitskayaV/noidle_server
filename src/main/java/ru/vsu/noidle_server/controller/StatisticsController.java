package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.StatisticsDto;
import ru.vsu.noidle_server.service.StatisticsService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody List<StatisticsDto> statistics, @RequestParam("userId") UUID userId, @RequestParam("teamId") UUID teamId) {
        try {
            statisticsService.save(statistics, userId, teamId);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    //to check plugin interaction with server
    @GetMapping(value = "/check")
    public ResponseEntity save() {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "userId") UUID userId,
                                 @RequestParam(value = "date", required = false) OffsetDateTime date) {
        if (date == null) {
            date = OffsetDateTime.now();
        }

        //for debug
        List<StatisticsDto> statisticsDtos = statisticsService.getAll(userId, date);
        return ResponseEntity.ok(statisticsDtos);
    }
}
