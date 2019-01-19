package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.Constants;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.UpdateRole;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.service.SetupService;
import ru.vsu.noidle_server.service.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class SetupServiceImpl implements SetupService {

    private final UserService userService;

    @Override
    public void finishSetup(String password) throws ServiceException {
        userService.save(new UserEntity(Constants.ADMIN_EMAIL, Constants.ADMIN_NAME, password, UpdateRole.ADMIN), false);
    }

    @Override
    public boolean setupFinished() {
        return userService.anyAdminsExists();
    }
}
