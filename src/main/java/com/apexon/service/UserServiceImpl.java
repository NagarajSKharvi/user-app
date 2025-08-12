package com.apexon.service;

import com.apexon.dto.request.UserRequest;
import com.apexon.entity.User;
import com.apexon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    protected static final String logPrefix = "{} : {}";

    private final UserRepository userRepository;

    @Override
    public User getProfile(Authentication authentication) {
        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        return userRepository.findByUsername(authentication.getName()).orElseThrow();
    }

    @Override
    public User updateProfile(Authentication authentication, UserRequest userRequest) {
        log.info(logPrefix, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setContact(userRequest.getContact());
        return userRepository.save(user);
    }
}
