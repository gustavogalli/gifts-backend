package com.galli.gifts.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginRequestTest {

    @Test
    public void testGettersAndSetters() {
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setUsername("johndoe");
        loginRequest.setPassword("password123");

        assertEquals("johndoe", loginRequest.getUsername());
        assertEquals("password123", loginRequest.getPassword());
    }
}
