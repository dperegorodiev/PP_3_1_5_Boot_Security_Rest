package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    List<User> findAll();

    User findOne(int id);

    void saveUser(User user);

    void update(int id, User updatedUser);

    void deleteUser(int id);
}
