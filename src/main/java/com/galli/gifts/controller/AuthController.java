package com.galli.gifts.controller;

import com.galli.gifts.dto.LoginRequest;
import com.galli.gifts.dto.LoginResponse;
import com.galli.gifts.dto.RegisterRequest;
import com.galli.gifts.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.ok("User successfully registered!");
    }
}
