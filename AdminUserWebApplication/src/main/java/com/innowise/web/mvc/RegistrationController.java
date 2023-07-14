//package com.innowise.web.mvc;
//
//import com.innowise.model.Role;
//import com.innowise.model.User;
//import com.innowise.repository.crud_service.mvc.UserCrudService;
//import org.springframework.beans.factory.annotation.Autowired;
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
//    private final UserCrudService userCrudService;
//
//    @Autowired
//    public RegistrationController(UserCrudService userCrudService) {
//        this.userCrudService = userCrudService;
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
//        return userCrudService.registerNewUser(user, errors);
//    }
//}
