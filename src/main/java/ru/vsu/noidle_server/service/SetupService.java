package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.exception.ServiceException;

public interface SetupService {

    void finishSetup(String password) throws ServiceException;

    boolean setupFinished();
}
