package com.ryanpmartz.booktrackr.authentication;

import io.jsonwebtoken.lang.Assert;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public class JwtAuthenticationToken implements Authentication {

    private final String email;
    private final Collection<? extends GrantedAuthority> roles;
    private final UUID userId;

    private boolean authenticated;

    public JwtAuthenticationToken(UUID userId, String email, Collection<? extends GrantedAuthority> authorities) {
        Assert.notEmpty(authorities, "Cannot construct JWT with empty authorities");

        this.userId = userId;
        this.email = email;
        this.roles = authorities;

        this.authenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    public UUID getUserId() {
        return userId;
    }
}
