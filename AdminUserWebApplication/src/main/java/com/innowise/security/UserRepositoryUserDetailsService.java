package com.innowise.security;

import com.innowise.repository.JdbcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private final JdbcUserRepository jdbcUserRepository;

    @Autowired
    public UserRepositoryUserDetailsService(JdbcUserRepository jdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return jdbcUserRepository.findByUsername(username);
    }
}
