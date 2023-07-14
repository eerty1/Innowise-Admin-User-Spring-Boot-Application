//package com.innowise.web.mvc;
//
//import com.innowise.model.dto.UserDTO;
//import com.innowise.repository.crud_service.mvc.UserCrudService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/userpool")
//public class UserPoolController {
//    private final UserCrudService userCrudService;
//
//    @Autowired
//    public UserPoolController(UserCrudService userCrudService) {
//        this.userCrudService = userCrudService;
//    }
//
//    @GetMapping
//    public String showUserPoolPage(Model model) {
//        return userCrudService.getAllUsers(model);
//    }
//
//    @GetMapping("/get/{id}")
//    public String showUserSettingsPage(@PathVariable Long id, Model model) {
//        return userCrudService.getUser(id, model);
//    }
//
//    @PatchMapping("/update/{id}")
//    public String changeUserData(@PathVariable Long id, @Valid UserDTO userDTO, Errors errors) {
//        return userCrudService.updateUser(id, userDTO, errors);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        return userCrudService.deleteUser(id);
//    }
//}
