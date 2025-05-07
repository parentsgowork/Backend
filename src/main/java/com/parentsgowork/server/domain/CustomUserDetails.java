package com.parentsgowork.server.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {
    private final Long id;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id) {
        super(username, password, authorities);
        this.id = id;
    }

    public CustomUserDetails(String username, Collection<? extends GrantedAuthority> authorities, Long id) {
        super("null", "null", authorities);
        this.id = id;
    }
}
