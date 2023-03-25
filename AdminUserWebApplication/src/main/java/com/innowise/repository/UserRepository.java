package com.innowise.repository;

import com.innowise.model.User;

import java.util.List;


public interface UserRepository {
    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    User save(User user);

    User update(User user);

    void deleteById(Long id);
}
