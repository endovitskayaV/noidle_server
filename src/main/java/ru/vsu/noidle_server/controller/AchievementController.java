package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.noidle_server.model.dto.AchievementDto;
import ru.vsu.noidle_server.service.AchievementService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/achievements")
public class AchievementController {

    private final AchievementService achievementService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AchievementDto> save(@RequestBody AchievementDto achievementDto) {
        return new ResponseEntity<>(achievementService.save(achievementDto), HttpStatus.OK);
    }

    //to check plugin interaction with server
    @GetMapping(value = "/check")
    public ResponseEntity save() {
        return ResponseEntity.ok().build();
    }
}
