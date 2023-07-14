package com.innowise.web.rest;

import com.innowise.model.User;
import com.innowise.repository.crud_service.rest.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class RegistrationController {
    private final UserCrudService userCrudService;

    @Autowired
    public RegistrationController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @PostMapping(path = "/registration", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        return userCrudService.registerNewUser(user);
    }
}
