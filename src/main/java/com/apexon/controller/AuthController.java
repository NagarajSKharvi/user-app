package com.apexon.controller;

import com.apexon.dto.request.LoginRequest;
import com.apexon.dto.request.UserRequest;
import com.apexon.entity.User;
import com.apexon.repository.UserRepository;
import com.apexon.resource.Response;
import com.apexon.service.AuthService;
import com.apexon.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

import static com.apexon.resource.Response.getResponse;
import static com.apexon.resource.Response.response;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    protected static final String logPrefix = "{} : {}";

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody UserRequest registerRequest) {
        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Response> refresh(@RequestBody Map<String, String> request) {
        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        String refreshToken = request.get("refresh-token");
        return authService.refresh(refreshToken);
    }
}
