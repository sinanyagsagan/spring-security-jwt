package io.synansoft.springsecurityjwt.controller;

import io.synansoft.springsecurityjwt.constant.UserConstant;
import io.synansoft.springsecurityjwt.dto.UserDto;
import io.synansoft.springsecurityjwt.dto.UserRequest;
import io.synansoft.springsecurityjwt.dto.UserResponse;
import io.synansoft.springsecurityjwt.entity.HttpResponse;
import io.synansoft.springsecurityjwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> register (@RequestBody UserDto userDto){
        UserResponse newUser = authenticationService.save(userDto);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", newUser))
                        .message(UserConstant.USER_CREATED)
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login (@RequestBody UserRequest userRequest){
        UserResponse loginUser = authenticationService.auth(userRequest);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", loginUser))
                        .message(UserConstant.USER_LOGGED_IN)
                        .status(HttpStatus.ACCEPTED)
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .build()
        );
    }
}
