package io.synansoft.springsecurityjwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("WELCOME DASHBOARD");
    }
}
