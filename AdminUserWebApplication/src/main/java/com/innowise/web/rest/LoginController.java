package com.innowise.web.rest;

import com.innowise.model.User;
import com.innowise.repository.crud_service.rest.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class LoginController {
    private final UserCrudService userCrudService;

    @Autowired
    public LoginController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @GetMapping(path = "/login", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody User user) {
        return userCrudService.loginUser(user);
    }
}
