package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    User findByUsername(String name);

    List<User> getAllUsers();

    User showUser(Long id);

    void deleteUserById(Long id);

    void updateUser(Long id, User user);

    void save(User user);

}
