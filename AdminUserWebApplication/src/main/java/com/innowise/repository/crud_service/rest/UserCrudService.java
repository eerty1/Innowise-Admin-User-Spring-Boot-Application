package com.innowise.repository.crud_service.rest;

import com.innowise.model.User;
import com.innowise.model.dto.UserDTO;
import com.innowise.model.mapper.UserMapper;
import com.innowise.repository.JdbcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Service
public class UserCrudService {
    private final JdbcUserRepository jdbcUserRepository;
    private final UserMapper userMapper = UserMapper.userMapperInstance;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final String successfulLoginMessage;
    private final String successfulDeletionMessage;

    @Autowired
    public UserCrudService(JdbcUserRepository jdbcUserRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           @Value("${response-messages.success.user-logged-in}") String successfulLoginMessage,
                           @Value("${response-messages.success.user-deleted}") String successfulDeletionMessage)
    {
        this.jdbcUserRepository = jdbcUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.successfulLoginMessage = successfulLoginMessage;
        this.successfulDeletionMessage = successfulDeletionMessage;
    }

    public ResponseEntity<String> loginUser(User user) throws BadCredentialsException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(successfulLoginMessage, OK);
    }

    public ResponseEntity<User> registerNewUser(User user) {
        return new ResponseEntity<>(jdbcUserRepository.save(user.toUser(passwordEncoder)), CREATED);
    }

    public ResponseEntity<List<UserDTO>> showAllUsers() {
        return new ResponseEntity<>(userMapper.toDTOs(jdbcUserRepository.findAll()), OK);
    }

    public ResponseEntity<UserDTO> showUser(Long id) {
        return new ResponseEntity<>(userMapper.toDTO(jdbcUserRepository.findById(id)), OK);
    }

    public ResponseEntity<UserDTO> updateUser(Long id, UserDTO userDTO) {
        User userToUpdate = jdbcUserRepository.findById(id);
        jdbcUserRepository.update(userMapper.updateUserFromDTO(userDTO, userToUpdate));
        return new ResponseEntity<>(userMapper.toDTO(userToUpdate), OK);
    }

    public ResponseEntity<String> deleteUser(Long id) {
        jdbcUserRepository.deleteById(id);
        return new ResponseEntity<>(successfulDeletionMessage, OK);
    }
}
