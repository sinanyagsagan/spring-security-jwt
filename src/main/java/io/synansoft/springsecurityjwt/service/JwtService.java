package io.synansoft.springsecurityjwt.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String findUsername(String token);
    boolean tokenControl(String jwt, UserDetails userDetails);
    String generateToken(UserDetails user);
}
