package com.myLearning.timeIsMoney.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    private String password;

    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        this.id = user.getId();
        this.username = user.getLogin();
        this.password = user.getPassword();
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().getAuthority()));
    }

    public static UserDetailsImpl getUserDetailsFromUser(User user) {
        return new UserDetailsImpl(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
