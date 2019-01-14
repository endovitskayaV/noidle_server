package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.Constants;
import ru.vsu.noidle_server.model.dto.LoginDto;
import ru.vsu.noidle_server.service.AuthenticationService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    @Override
    public boolean login(LoginDto loginDto) {
        if (loginDto == null || loginDto.getName() == null || loginDto.getPassword() == null) {
            return false;
        }
        try {
            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(
                    loginDto.getName(),
                    loginDto.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(Constants.DEFAULT_AUTH_ROLE)));
            Authentication authentication = authenticationManager.authenticate(user);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            return false;
        }
        return true;
    }
}
