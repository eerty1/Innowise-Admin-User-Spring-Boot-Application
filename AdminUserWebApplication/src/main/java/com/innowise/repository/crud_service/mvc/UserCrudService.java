//package com.innowise.repository.crud_service.mvc;
//
//import com.innowise.model.User;
//import com.innowise.model.dto.UserDTO;
//import com.innowise.model.mapper.UserMapper;
//import com.innowise.repository.JdbcUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//
//@Service
//public class UserCrudService {
//    private final JdbcUserRepository jdbcUserRepository;
//    private final UserMapper userMapper = UserMapper.userMapperInstance;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public UserCrudService(JdbcUserRepository jdbcUserRepository, PasswordEncoder passwordEncoder) {
//        this.jdbcUserRepository = jdbcUserRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public String registerNewUser(User user, Errors errors) {
//        if (errors.hasErrors()) {
//            return "userRegistrationPage";
//        }
//        jdbcUserRepository.save(user.toUser(passwordEncoder));
//        return "userRegistrationPage";
//    }
//
//    public String getAllUsers(Model model) {
//        Iterable<UserDTO> users = userMapper.toDTOs(jdbcUserRepository.findAll());
//        model.addAttribute("users", users);
//        return "userPoolPage";
//    }
//
//    public String getUser(Long id, Model model) {
//        User user = jdbcUserRepository.findById(id);
//        UserDTO userDTO = userMapper.toDTO(user);
//        model.addAttribute("userDTO", userDTO);
//        return "userSettings";
//    }
//
//    public String updateUser(Long id, UserDTO userDTO, Errors errors) {
//        if (errors.hasErrors()) {
//            return "userSettings";
//        }
//        User userToUpdate = jdbcUserRepository.findById(id);
//        jdbcUserRepository.update(userMapper.updateUserFromDTO(userDTO, userToUpdate));
//        return "redirect:/userpool";
//    }
//
//    public String deleteUser(Long id) {
//        jdbcUserRepository.deleteById(id);
//        return "redirect:/userpool";
//    }
//}
