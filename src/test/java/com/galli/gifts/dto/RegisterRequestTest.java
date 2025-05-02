package com.galli.gifts.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterRequestTest {

    @Test
    public void testGettersAndSetters() {
        RegisterRequest registerRequest = new RegisterRequest();

        registerRequest.setFullName("John Doe");
        registerRequest.setCity("New York");
        registerRequest.setUsername("johndoe");
        registerRequest.setPassword("password123");

        assertEquals("John Doe", registerRequest.getFullName());
        assertEquals("New York", registerRequest.getCity());
        assertEquals("johndoe", registerRequest.getUsername());
        assertEquals("password123", registerRequest.getPassword());
    }
}
