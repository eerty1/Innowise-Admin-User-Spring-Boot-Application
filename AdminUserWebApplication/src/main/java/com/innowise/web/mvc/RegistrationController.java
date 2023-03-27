//package com.innowise.web.mvc;
//
//import com.innowise.model.Role;
//import com.innowise.model.User;
//import com.innowise.model.mapper.UserMapper;
//import com.innowise.repository.JdbcUserRepository;
//import org.mapstruct.Mapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.validation.Valid;
//import java.util.List;
//
//import static com.innowise.model.Role.*;
//
//
//@Controller
//public class RegistrationController {
//    private final JdbcUserRepository jdbcUserRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public RegistrationController(JdbcUserRepository jdbcUserRepository, PasswordEncoder passwordEncoder) {
//        this.jdbcUserRepository = jdbcUserRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @ModelAttribute(name = "user")
//    public User validateUser() {
//        return new User();
//    }
//
//    @ModelAttribute(name = "role")
//    public List<Role> getAvailableRoles() {
//        return List.of(ADMIN, USER);
//    }
//
//    @GetMapping("/registration")
//    public String showRegistrationPage() {
//        return "userRegistrationPage";
//    }
//
//    @PostMapping("/registration")
//    public String registerUser(@Valid User user, Errors errors) {
//        if (errors.hasErrors()) {
//            return "userRegistrationPage";
//        }
//        jdbcUserRepository.save(user.toUser(passwordEncoder));
//
//        return "userRegistrationPage";
//    }
//}
