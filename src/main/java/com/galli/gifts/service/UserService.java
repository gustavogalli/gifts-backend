package com.galli.gifts.service;

import com.galli.gifts.dto.LoginRequest;
import com.galli.gifts.dto.RegisterRequest;

public interface UserService {

    String login(LoginRequest loginRequest);
    void register(RegisterRequest registerRequest);
}
