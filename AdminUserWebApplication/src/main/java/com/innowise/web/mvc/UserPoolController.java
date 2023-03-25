//package com.innowise.web.mvc;
//
//import com.innowise.model.dto.UserDTO;
//import com.innowise.model.mapper.UserMapper;
//import com.innowise.repository.JdbcUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/userpool")
//public class UserPoolController {
//    private final UserMapper userMapper = UserMapper.userMapperInstance;
//    private final JdbcUserRepository jdbcUserRepository;
//
//    @Autowired
//    public UserPoolController(JdbcUserRepository jdbcUserRepository) {
//        this.jdbcUserRepository = jdbcUserRepository;
//    }
//
//    @GetMapping
//    public String showUserPoolPage(Model model) {
//        Iterable<UserDTO> users = userMapper.toDTOs(jdbcUserRepository.findAll());
//        model.addAttribute("users", users);
//
//        return "userPoolPage";
//    }
//}
