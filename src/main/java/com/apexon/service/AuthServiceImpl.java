package com.apexon.service;

import com.apexon.dto.request.LoginRequest;
import com.apexon.dto.request.UserRequest;
import com.apexon.entity.User;
import com.apexon.repository.UserRepository;
import com.apexon.resource.Response;
import com.apexon.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Optional;

import static com.apexon.resource.Response.response;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    protected static final String logPrefix = "{} : {}";

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<Response> register(@RequestBody UserRequest registerRequest) {
        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return response(HttpStatus.BAD_REQUEST.value(), "Username already taken");
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setContact(registerRequest.getContact());
        userRepository.save(user);
        return response(HttpStatus.CREATED.value(), "User registered successfully");
    }

    @Override
    public ResponseEntity<Response> login(LoginRequest loginRequest) {
        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
        if (optionalUser.isPresent()) {
            if (passwordEncoder.matches(loginRequest.getPassword(), optionalUser.get().getPassword())) {
                String accessToken = jwtUtil.generateAccessToken(optionalUser.get().getUsername());
                String refreshToken = jwtUtil.generateRefreshToken(optionalUser.get().getUsername());
                return response(new Response(HttpStatus.OK.value(), "Login Successful", Map.of("access-token", accessToken,
                        "refresh-token", refreshToken,
                        "name", optionalUser.get().getName())));
            }
            return response(HttpStatus.UNAUTHORIZED.value(), "Invalid Credentials");
        }
        return response(HttpStatus.NOT_FOUND.value(), "Email not registered");
    }

    @Override
    public ResponseEntity<Response> refresh(String refreshToken) {
        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        if (refreshToken == null || jwtUtil.isTokenExpired(refreshToken) || !jwtUtil.isValid(refreshToken)) {
            return response(HttpStatus.UNAUTHORIZED.value(), "Invalid refresh token");
        }
        String username = jwtUtil.extractUsername(refreshToken);
        String accessToken = jwtUtil.generateAccessToken(username);
        return response(new Response(HttpStatus.OK.value(), "Refresh Successful", Map.of("access-token", accessToken)));
    }
}
