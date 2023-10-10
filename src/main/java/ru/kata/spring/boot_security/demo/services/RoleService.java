package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getAllUsers();

    void save(Role role);

    void deleteById(Integer id);

    Role showUserById(Integer id);
}
