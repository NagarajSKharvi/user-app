package com.apexon.controller;

import com.apexon.dto.request.UserRequest;
import com.apexon.entity.User;
import com.apexon.repository.UserRepository;
import com.apexon.resource.Response;
import com.apexon.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.apexon.resource.Response.getResponse;
import static com.apexon.resource.Response.updateResponse;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    protected static final String logPrefix = "{} : {}";

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<Response> getProfile(Authentication authentication) {
        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        User user = userService.getProfile(authentication);
        return getResponse(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<Response> updateProfile(Authentication authentication, @RequestBody UserRequest userRequest) {
        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        User user = userService.updateProfile(authentication, userRequest);
        return updateResponse(user);
    }
}
