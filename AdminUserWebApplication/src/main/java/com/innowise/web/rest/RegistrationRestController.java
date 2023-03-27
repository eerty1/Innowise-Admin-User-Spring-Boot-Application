package com.innowise.web.rest;

import com.innowise.model.User;
import com.innowise.repository.JdbcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class RegistrationRestController {
    private final JdbcUserRepository jdbcUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationRestController(JdbcUserRepository jdbcUserRepository, PasswordEncoder passwordEncoder) {
        this.jdbcUserRepository = jdbcUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/registration", consumes = "application/json")
    public User registerUser(@Valid @RequestBody User user) {
        return jdbcUserRepository.save(user.toUser(passwordEncoder));
    }
}
