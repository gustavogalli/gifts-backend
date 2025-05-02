package com.galli.gifts.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginResponseTest {

    @Test
    public void testConstructorAndGetters() {
        String token = "mockToken";
        String username = "testuser";
        String fullName = "Test User";
        LoginResponse loginResponse = new LoginResponse(token, username, fullName);

        assertEquals(token, loginResponse.getToken());
        assertEquals(username, loginResponse.getUsername());
        assertEquals(fullName, loginResponse.getFullName());
    }

    @Test
    public void testSetters() {
        LoginResponse loginResponse = new LoginResponse("mockToken", "testuser", "Test User");

        loginResponse.setToken("newMockToken");
        loginResponse.setUsername("newUsername");
        loginResponse.setFullName("New Test User");

        assertEquals("newMockToken", loginResponse.getToken());
        assertEquals("newUsername", loginResponse.getUsername());
        assertEquals("New Test User", loginResponse.getFullName());
    }
}
