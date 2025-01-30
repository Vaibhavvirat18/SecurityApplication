package com.vvcoders.SecurityApp.SecurityApplication.config;

import com.vvcoders.SecurityApp.SecurityApplication.filters.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.vvcoders.SecurityApp.SecurityApplication.enums.Role.ADMIN;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/posts/**").hasRole(ADMIN.name())
                        .anyRequest().authenticated()) // All other endpoints require authentication
                        .csrf(csrfConfig-> csrfConfig.disable()) // Disable CSRF for APIs
                        .sessionManagement(sessionConfig->
                                sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
    }

//    @Bean
//    UserDetailsService myInMemoryUserDetailsService(){
//        UserDetails normalUser = User
//                .withUsername("virat")
//                .password(passwordEncoder().encode("virat123@"))
//                .roles("NORMAL_USER")
//                .build();
//
//
//        UserDetails admin = User
//                .withUsername("vaibhav")
//                .password(passwordEncoder().encode("vv187@"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser, admin);
//
//    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
