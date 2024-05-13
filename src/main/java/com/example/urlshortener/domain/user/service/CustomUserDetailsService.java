package com.example.urlshortener.domain.user.service;

import com.example.urlshortener.domain.user.entity.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if(loginAttemptService.isBlocked(username)) {
            throw new LockedException("User Account is Locked");
        }

        ApplicationUser applicationUser = userService.findByUsername(username);
        if(applicationUser == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }

        return new User(applicationUser.getUsername(), applicationUser.getPassword(), new ArrayList<>());
    }
}
