package com.apexon.service;

import com.apexon.dto.request.UserRequest;
import com.apexon.entity.User;
import org.springframework.security.core.Authentication;

public interface UserService {

    User getProfile(Authentication authentication);

    User updateProfile(Authentication authentication, UserRequest userRequest);
}
