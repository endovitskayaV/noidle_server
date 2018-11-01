package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.UserDto;
import ru.vsu.noidle_server.model.mapper.UserMapper;
import ru.vsu.noidle_server.model.repository.UserRepository;
import ru.vsu.noidle_server.service.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto saveUser(OAuth2Authentication user) {
        UserEntity userEntity = userMapper.authToEntity(user);
        UserEntity existingUser = userRepository.findByEmail(userEntity.getEmail());
        if (existingUser != null) {
            userEntity.setId(existingUser.getId());
        }
        userRepository.save(userEntity);
        log.info("Saved {}", userEntity);
        return userMapper.authToEntity(userEntity);
    }

    @Override
    public UserDto getUser(OAuth2Authentication user) {
        return userMapper.authToDto(user);
    }
}
