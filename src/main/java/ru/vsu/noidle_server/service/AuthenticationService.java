package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.model.dto.LoginDto;

public interface AuthenticationService {
    boolean login(LoginDto loginDto);
}
