package com.innowise.web.rest;

import com.innowise.model.dto.UserDTO;
import com.innowise.model.mapper.UserMapper;
import com.innowise.repository.JdbcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/userpool", produces = "application/json")
public class UserPoolRestController {
    private final JdbcUserRepository jdbcUserRepository;
    private final UserMapper userMapper = UserMapper.userMapperInstance;

    @Autowired
    public UserPoolRestController(JdbcUserRepository jdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @GetMapping(path = "/user/{id}", produces = "application/json")
    public UserDTO showCurrentUser(@PathVariable Long id) {
        return userMapper.toDTO(jdbcUserRepository.findById(id));
    }

    @GetMapping
    public List<UserDTO> showUserPoolPage() {
        return userMapper.toDTOs(jdbcUserRepository.findAll());
    }
}
