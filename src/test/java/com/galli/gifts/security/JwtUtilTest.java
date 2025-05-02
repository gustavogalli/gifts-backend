package com.galli.gifts.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    @Test
    public void testGenerateAndExtractToken() {
        JwtUtil jwtUtil = new JwtUtil();
        String username = "testuser";
        String token = jwtUtil.generateToken(username);

        assertNotNull(token);
        String extractedUsername = jwtUtil.extractUsername(token);
        assertEquals(username, extractedUsername);
    }
}
