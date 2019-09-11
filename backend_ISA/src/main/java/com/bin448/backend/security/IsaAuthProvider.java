package com.bin448.backend.security;

import com.bin448.backend.entity.User;
import com.bin448.backend.exception.NotFoundException;
import com.bin448.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class IsaAuthProvider  implements AuthenticationProvider {

    private UserService userService;
    private PasswordEncoder passwordEncoder;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        try {
            User user = userService.getUserByUsername(username);
            if (!passwordEncoder.matches(password, user.getPassword()) || user.isActive()==false) {
                throw new BadCredentialsException("Authentication failed");
            }
            return new UsernamePasswordAuthenticationToken(
                username, password, Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
        } catch (NotFoundException nfo) {
            throw new BadCredentialsException("Authentication failed");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
