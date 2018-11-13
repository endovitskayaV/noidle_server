package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.NotificationDto;

import java.util.UUID;

public interface NotificationService {

    NotificationDto get(UUID userId) throws ServiceException;
}
