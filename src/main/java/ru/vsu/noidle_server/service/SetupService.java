package ru.vsu.noidle_server.service;

public interface SetupService {

    void finishSetup(String password);

    boolean setupFinished();
}
