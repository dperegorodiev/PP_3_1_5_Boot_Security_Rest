package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String name);

    List<User> getAllUsers();

    User showUser(Long id);

    void deleteUserById(Long id);

    void updateUser(User user);

    void saveUser(User user);

}
