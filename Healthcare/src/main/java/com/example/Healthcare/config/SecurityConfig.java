package com.example.Healthcare.config;

import com.example.Healthcare.service.CustomUserDetailsService;
import com.example.Healthcare.utils.JwtAuthenticationFilter;
import com.example.Healthcare.utils.JwtAuthorizationFilter;
import com.example.Healthcare.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // ✅ Use authenticationConfiguration to get the auth manager
        AuthenticationManager authManager = authenticationConfiguration.getAuthenticationManager();
        JwtAuthenticationFilter authFilter = new JwtAuthenticationFilter(authManager, jwtUtil);

        authFilter.setFilterProcessesUrl("/authenticate");
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/authenticate", "/token").permitAll()
                        .requestMatchers("/api/patients/**").authenticated()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(authFilter)
                .addFilterBefore(new JwtAuthorizationFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Replace with BCryptPasswordEncoder for production
    }
}