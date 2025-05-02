package com.galli.gifts.service.impl;

import com.galli.gifts.dto.LoginRequest;
import com.galli.gifts.dto.LoginResponse;
import com.galli.gifts.dto.RegisterRequest;
import com.galli.gifts.entity.User;
import com.galli.gifts.repository.UserRepository;
import com.galli.gifts.security.JwtUtil;
import com.galli.gifts.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getUsername());
                LoginResponse response = new LoginResponse(token, user.getUsername(), user.getFullName());
                return response;
            }
        }
        throw new RuntimeException("Credenciais inválidas");
    }

    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setFullName(registerRequest.getFullName());
        user.setCity(registerRequest.getCity());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(user);
    }
}
