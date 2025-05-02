package com.galli.gifts.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JwtAuthenticationFilterTest {

    private JwtUtil jwtUtil;
    private JwtAuthenticationFilter filter;

    @BeforeEach
    public void setUp() {
        jwtUtil = mock(JwtUtil.class);
        filter = new JwtAuthenticationFilter(jwtUtil);
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testDoFilterInternal_WithValidToken() throws ServletException, IOException {
        String token = "valid.jwt.token";
        String username = "testuser";

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtil.extractUsername(token)).thenReturn(username);

        filter.doFilterInternal(request, response, chain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(username, SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        verify(chain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_WithoutAuthorizationHeader() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn(null);

        filter.doFilterInternal(request, response, chain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(chain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_WithInvalidHeaderFormat() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("InvalidHeader");

        filter.doFilterInternal(request, response, chain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(chain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_WithAlreadyAuthenticatedUser() throws ServletException, IOException {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("testuser", null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer token");

        filter.doFilterInternal(request, response, chain);

        assertEquals(auth, SecurityContextHolder.getContext().getAuthentication());
        verify(chain).doFilter(request, response);
    }
}
