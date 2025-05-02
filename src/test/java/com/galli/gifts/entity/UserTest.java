package com.galli.gifts.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testGettersAndSetters() {
        User user = new User();

        user.setId(1L);
        user.setFullName("John Doe");
        user.setCity("New York");
        user.setUsername("johndoe");
        user.setPassword("password123");

        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getFullName());
        assertEquals("New York", user.getCity());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password123", user.getPassword());
    }
}
