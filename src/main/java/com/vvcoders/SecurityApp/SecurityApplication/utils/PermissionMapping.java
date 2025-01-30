package com.vvcoders.SecurityApp.SecurityApplication.utils;

import com.vvcoders.SecurityApp.SecurityApplication.enums.Permission;
import com.vvcoders.SecurityApp.SecurityApplication.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vvcoders.SecurityApp.SecurityApplication.enums.Permission.*;
import static com.vvcoders.SecurityApp.SecurityApplication.enums.Role.*;

public class PermissionMapping {

    private static Map<Role, Set<Permission>> map = Map.of(
            USER, Set.of(USER_VIEW, POST_VIEW),
            CREATOR, Set.of(POST_CREATE, POST_UPDATE),
            ADMIN, Set.of(USER_VIEW, USER_CREATE, USER_UPDATE, USER_DELETE, POST_VIEW, POST_CREATE, POST_UPDATE, POST_DELETE)
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role){

        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}
