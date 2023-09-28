package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Component
public class UserAdd implements ApplicationRunner {
    private final UserRepository userRepository;

    @Autowired
    public UserAdd (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User admin = new User("admin", "admin");
        User user = new User("user", "user");

        userRepository.save(admin);
        userRepository.save(user);

    }
}
