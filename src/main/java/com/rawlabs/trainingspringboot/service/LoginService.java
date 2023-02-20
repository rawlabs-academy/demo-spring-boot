package com.rawlabs.trainingspringboot.service;

import com.rawlabs.trainingspringboot.component.TokenProvider;
import com.rawlabs.trainingspringboot.domain.dao.User;
import com.rawlabs.trainingspringboot.domain.dto.LoginRequestDto;
import com.rawlabs.trainingspringboot.domain.dto.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenProvider tokenProvider;

    @Autowired
    public LoginService(AuthenticationManager authenticationManager, UserService userService,
                        TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    public LoginResponseDto doLogin(LoginRequestDto request) {
        User user = (User) userService.loadUserByUsername(request.getUsername());

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid username or password!");
        }

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);

        return LoginResponseDto.builder()
                .accessToken(token)
                .expiresIn(tokenProvider.getExpirationDate(token))
                .build();
    }

}
