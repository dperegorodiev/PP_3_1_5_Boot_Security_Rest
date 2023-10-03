package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleRepository roleRepository, RoleService roleService) {
        this.userService = userService;

        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String showUser(Model model) {
        model.addAttribute("allusers", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/add")
    public String addUser(@ModelAttribute("user") User user, Model model){
        List<Role> roles = roleService.allRoles();
        model.addAttribute("allRoles", roles);
        return "/add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.showUser(id));
        model.addAttribute("roles", roleRepository.findAll());
        return "/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/admin/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }
}
