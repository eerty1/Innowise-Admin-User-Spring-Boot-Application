package com.innowise.web.rest;

import com.innowise.model.dto.UserDTO;
import com.innowise.repository.crud_service.rest.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/userpool", produces = APPLICATION_JSON_VALUE)
public class UserPoolController {
    private final UserCrudService userCrudService;

    @Autowired
    public UserPoolController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> showCurrentUser(@PathVariable Long id) {
        return userCrudService.showUser(id);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> showUserPoolPage() {
        return userCrudService.showAllUsers();
    }

    @PatchMapping(path = "/update/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changeUserData(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        return userCrudService.updateUser(id, userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userCrudService.deleteUser(id);
    }
}
