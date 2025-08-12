package com.apexon.service;

import com.apexon.dto.request.LoginRequest;
import com.apexon.dto.request.UserRequest;
import com.apexon.resource.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    ResponseEntity<Response> register(@RequestBody UserRequest registerRequest);

    ResponseEntity<Response> login(LoginRequest loginRequest);

    ResponseEntity<Response> refresh(String refreshToken);
}
