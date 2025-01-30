package com.vvcoders.SecurityApp.SecurityApplication.dto;


import com.vvcoders.SecurityApp.SecurityApplication.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {

    private String email;
    private String password;
    private String name;
    private Set<Role> roles;

}
