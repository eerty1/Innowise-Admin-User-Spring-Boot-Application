package com.innowise.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements UserDetails {
    private static final String NOT_BLANK = "must not be blank";
    private static final String NOT_NULL = "must not be null";
    private static final String NOT_IN_RANGE = "must not be longer than {max}";
    private static final String DOESNT_MATCH_FORMAT = "must match belarusian format";
    private static final String PHONE_REGEX = "^(\\+375)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";
    private Long id;
    @NotBlank(message = NOT_BLANK)
    @Size(max = 30, message = "username" + NOT_IN_RANGE)
    private String username;
    @NotBlank(message = NOT_BLANK)
    @Size(max = 100, message = "password" + NOT_IN_RANGE)
    private String password;
    @NotNull(message = NOT_NULL)
    private Role role;
    @NotBlank(message = NOT_BLANK)
    @Size(max = 30, message = NOT_IN_RANGE)
    private String fullName;
    @NotBlank(message = NOT_BLANK)
    @Size(max = 30, message = NOT_IN_RANGE)
    private String position;
    @NotBlank(message = NOT_BLANK)
    @Size(max = 30, message = NOT_IN_RANGE)
    private String department;
    @NotBlank(message = NOT_BLANK)
    @Size(max = 30, message = NOT_IN_RANGE)
    private String address;
    @Pattern(regexp = PHONE_REGEX, message = DOESNT_MATCH_FORMAT)
    private String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(id, username, passwordEncoder.encode(password), role, fullName, position, department, address, phoneNumber);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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

