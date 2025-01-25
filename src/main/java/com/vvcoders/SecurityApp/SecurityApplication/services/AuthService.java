package com.vvcoders.SecurityApp.SecurityApplication.services;

import com.vvcoders.SecurityApp.SecurityApplication.dto.LoginDTO;
import com.vvcoders.SecurityApp.SecurityApplication.dto.SignUpDTO;
import com.vvcoders.SecurityApp.SecurityApplication.dto.UsersDTO;
import com.vvcoders.SecurityApp.SecurityApplication.entities.Users;
import com.vvcoders.SecurityApp.SecurityApplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public UsersDTO signUp(SignUpDTO signUpDTO) {
        Optional<Users> user =userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with email already exits "+signUpDTO.getEmail());
        }
        Users toBeCreated = modelMapper.map(signUpDTO, Users.class);
        toBeCreated.setPassword(passwordEncoder.encode(toBeCreated.getPassword()));
        Users savedUser= userRepository.save(toBeCreated);
        return modelMapper.map(savedUser, UsersDTO.class);
    }

    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        Users user = (Users) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }
}
