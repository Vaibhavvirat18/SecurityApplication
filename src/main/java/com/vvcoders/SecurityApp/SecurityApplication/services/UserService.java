package com.vvcoders.SecurityApp.SecurityApplication.services;

import com.vvcoders.SecurityApp.SecurityApplication.entities.Users;
import com.vvcoders.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.vvcoders.SecurityApp.SecurityApplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("User with email "+  username+ " not found"));
    }

    public Users findByUsername(String userName) {
        return userRepository.findByEmail(userName)
                .orElseThrow(()-> new ResourceNotFoundException("User with email "+  userName+ " not found"));
    }

    public Users findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with id "+  id+ " not found"));
    }
}
