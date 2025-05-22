package com.example.Healthcare.utils;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;
import java.util.List;

public class JwtAuthorizationFilter extends GenericFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    // ✅ Add this constructor
    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtUtil.isTokenValid(token)) {
                String username = jwtUtil.extractUsername(token);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        username, null, List.of()  // ← no roles needed
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        chain.doFilter(req, res);
    }
}