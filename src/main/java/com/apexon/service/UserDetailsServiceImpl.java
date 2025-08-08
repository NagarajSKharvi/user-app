package com.apexon.service;

import com.apexon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
            .map(user -> User.withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
            .build())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
