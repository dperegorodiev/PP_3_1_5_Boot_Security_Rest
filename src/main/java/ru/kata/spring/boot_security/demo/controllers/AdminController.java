package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping
    public String getAllUsers(@ModelAttribute ("user") User user, Principal principal, Model model) {
        User authenticatedUser = userService.findByUsername(principal.getName());
        model.addAttribute ("authenticatedUser", authenticatedUser);
        model.addAttribute ("roleOfAuthenticatedUser", authenticatedUser.getRoles());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute( "AllRoles", roleService.findAll());
        return "admin";
    }

    @GetMapping("/add")
    public String adduser(Model model, User user) {
        model.addAttribute("user", new User());
        List<Role> roles = (List<Role>) roleService.findAll();
        model.addAttribute("AllRoles", roles);
        return "redirect:/admin";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }
    //Страница юзера
    @GetMapping("/user-profile/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        User user = userService.showUser(id);
        model.addAttribute("user", user);
        model.addAttribute("AllRoles", user.getRoles());
        return "user";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.showUser(id));
        model.addAttribute("roles", roleService.findAll());
        return "redirect:/admin";
    }

    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
