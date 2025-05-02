package com.galli.gifts.service.impl;

import com.galli.gifts.dto.LoginRequest;
import com.galli.gifts.dto.LoginResponse;
import com.galli.gifts.dto.RegisterRequest;
import com.galli.gifts.entity.User;
import com.galli.gifts.repository.UserRepository;
import com.galli.gifts.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setFullName("Test User");
        user.setCity("Test City");
        user.setPassword("encodedPassword");
    }

    @Test
    public void testLoginSuccess() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("password");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", user.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(user.getUsername())).thenReturn("mockToken");

        LoginResponse loginResponse = userService.login(loginRequest);

        assertNotNull(loginResponse);
        assertEquals("mockToken", loginResponse.getToken());
        assertEquals("testuser", loginResponse.getUsername());
        assertEquals("Test User", loginResponse.getFullName());

        verify(userRepository, times(1)).findByUsername("testuser");
        verify(passwordEncoder, times(1)).matches("password", user.getPassword());
        verify(jwtUtil, times(1)).generateToken(user.getUsername());
    }

    @Test
    public void testLoginFailure_InvalidUsername() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("wronguser");
        loginRequest.setPassword("password");

        when(userRepository.findByUsername("wronguser")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> userService.login(loginRequest));

        assertEquals("Credenciais inválidas", exception.getMessage());

        verify(userRepository, times(1)).findByUsername("wronguser");
        verify(passwordEncoder, times(0)).matches(Mockito.anyString(), Mockito.anyString());
        verify(jwtUtil, times(0)).generateToken(Mockito.anyString());
    }

    @Test
    public void testLoginFailure_InvalidPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("wrongpassword");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongpassword", user.getPassword())).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> userService.login(loginRequest));

        assertEquals("Credenciais inválidas", exception.getMessage());

        verify(userRepository, times(1)).findByUsername("testuser");
        verify(passwordEncoder, times(1)).matches("wrongpassword", user.getPassword());
        verify(jwtUtil, times(0)).generateToken(Mockito.anyString());
    }

    @Test
    public void testRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFullName("Test User");
        registerRequest.setCity("Test City");
        registerRequest.setUsername("testuser");
        registerRequest.setPassword("password");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        userService.register(registerRequest);

        verify(userRepository, times(1)).save(Mockito.any(User.class));
        verify(passwordEncoder, times(1)).encode("password");
    }
}
