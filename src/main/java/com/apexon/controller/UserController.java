package com.apexon.controller;

import com.apexon.dto.request.UserRequest;
import com.apexon.entity.User;
import com.apexon.repository.UserRepository;
import com.apexon.resource.Response;
import lombok.RequiredArgsConstructor;
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
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/profile")
    public ResponseEntity<Response> getProfile(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        return getResponse(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<Response> updateProfile(Authentication authentication, @RequestBody UserRequest userRequest) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setContact(userRequest.getContact());
        user = userRepository.save(user);
        return updateResponse(user);
    }
}
