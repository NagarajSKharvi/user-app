package com.apexon.controller;

import com.apexon.dto.request.LoginRequest;
import com.apexon.dto.request.UserRequest;
import com.apexon.entity.User;
import com.apexon.repository.UserRepository;
import com.apexon.resource.Response;
import com.apexon.util.JwtUtil;
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
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody UserRequest registerRequest) {
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

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
        if (optionalUser.isPresent()) {
            if (passwordEncoder.matches(loginRequest.getPassword(), optionalUser.get().getPassword())) {
                String token = jwtUtil.generateToken(optionalUser.get().getUsername());
                return response(new Response(HttpStatus.OK.value(), "Login Successful", Map.of("access-token", token,
                        "name", optionalUser.get().getName())));
            }
            return response(HttpStatus.UNAUTHORIZED.value(), "Invalid Credentials");
        }
        return response(HttpStatus.NOT_FOUND.value(), "Email not registered");
    }
}
