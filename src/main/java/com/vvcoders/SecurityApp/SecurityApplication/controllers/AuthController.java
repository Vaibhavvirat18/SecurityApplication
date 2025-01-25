package com.vvcoders.SecurityApp.SecurityApplication.controllers;

import com.vvcoders.SecurityApp.SecurityApplication.dto.LoginDTO;
import com.vvcoders.SecurityApp.SecurityApplication.dto.SignUpDTO;
import com.vvcoders.SecurityApp.SecurityApplication.dto.UsersDTO;
import com.vvcoders.SecurityApp.SecurityApplication.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UsersDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        UsersDTO usersDTO= authService.signUp(signUpDTO);
        return new ResponseEntity<>(usersDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response){
        String token = authService.login(loginDTO);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
