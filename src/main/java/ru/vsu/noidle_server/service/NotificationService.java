package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.NotificationDto;

import java.util.List;
import java.util.UUID;

public interface NotificationService {

    List<NotificationDto> getAll(UUID userId) throws ServiceException;

    void setNotifications(UUID userId, UUID teamId) throws ServiceException;
}
