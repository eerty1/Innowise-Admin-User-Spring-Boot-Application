package com.innowise.web.rest;

import com.innowise.model.User;
import com.innowise.model.dto.UserDTO;
import com.innowise.model.mapper.UserMapper;
import com.innowise.repository.JdbcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/userpool", consumes = "application/json")
public class UserSettingsRestController {
    private final JdbcUserRepository jdbcUserRepository;
    private final UserMapper userMapper = UserMapper.userMapperInstance;

    @Autowired
    public UserSettingsRestController(JdbcUserRepository jdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @PatchMapping(path = "/update/{id}", consumes = "application/json")
    public User changeUserData(@PathVariable Long id, @RequestBody UserDTO userInstanceFromRequest) {
        User userToPatch = jdbcUserRepository.findById(id);
        return jdbcUserRepository.update(userMapper.updateUserFromDTO(userInstanceFromRequest, userToPatch));
    }

    @DeleteMapping("/update/{id}")
    public void deleteUser(@PathVariable Long id) {
        jdbcUserRepository.deleteById(id);
    }
}
