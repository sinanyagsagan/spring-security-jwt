package io.synansoft.springsecurityjwt.service.impl;

import io.synansoft.springsecurityjwt.dto.UserDto;
import io.synansoft.springsecurityjwt.dto.UserRequest;
import io.synansoft.springsecurityjwt.dto.UserResponse;
import io.synansoft.springsecurityjwt.entity.User;
import io.synansoft.springsecurityjwt.enums.UserRole;
import io.synansoft.springsecurityjwt.repository.UserRepository;
import io.synansoft.springsecurityjwt.service.AuthenticationService;
import io.synansoft.springsecurityjwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse save(UserDto userDto) {
        User user = User.builder().username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .isVerificationEmail(false)
                .role(UserRole.USER).build();
        userRepository.save(user);

        var token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }

    @Override
    public UserResponse auth(UserRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(),
                userRequest.getPassword()));

        User user = userRepository.findByUsername(userRequest.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }
}
