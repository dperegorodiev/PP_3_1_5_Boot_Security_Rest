package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;


import java.util.List;

@Controller
public class RegistrationController {
    private final RoleService roleService;
    private final UserService userService;

    public RegistrationController(RoleService roleService, UserService userService) {
        this.roleService = roleService;

        this.userService = userService;
    }
    @GetMapping("/registration")
    public String registrationList(@ModelAttribute("user") User user, Model model){
        List<Role> roles = roleService.allRoles();
        model.addAttribute("allRoles", roles);
        return "registration";
    }


    @PostMapping("/registration")
    public String userRegistration(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/loginPage";
    }

    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error!=null);
        model.addAttribute("logout", logout!=null);
        return "loginPage";
    }
}
