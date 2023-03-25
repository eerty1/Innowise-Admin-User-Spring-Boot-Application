//package com.innowise.web.mvc;
//
//import com.innowise.model.User;
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
//public class UserSettingsController {
//    private final JdbcUserRepository jdbcUserRepository;
//    private final UserMapper userMapper = UserMapper.userMapperInstance;
//
//    @Autowired
//    public UserSettingsController(JdbcUserRepository jdbcUserRepository) {
//        this.jdbcUserRepository = jdbcUserRepository;
//    }
//
//    @GetMapping("/update/{id}")
//    public String showUserSettingsPage(@PathVariable Long id, Model model) {
//        User user = jdbcUserRepository.findById(id);
//        UserDTO userDTO = userMapper.toDTO(user);
//        model.addAttribute("user", userDTO);
//        return "userSettings";
//    }
//
//    @PostMapping("/update/{id}")
//    public String changeUserData(@PathVariable Long id, UserDTO userInstanceFromRequest) {
//        User userToPatch = jdbcUserRepository.findById(id);
//        jdbcUserRepository.update(userMapper.updateUserFromDTO(userInstanceFromRequest, userToPatch));
//
//        return "redirect:/userpool";
//    }
//
//    @DeleteMapping("/update/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        jdbcUserRepository.deleteById(id);
//
//        return "redirect:/userpool";
//    }
//}
