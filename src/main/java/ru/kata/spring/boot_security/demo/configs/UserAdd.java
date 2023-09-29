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

    public UserAdd (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User admin = new User("admin", "$2a$12$cUtcTNTxeqBmJA40rp7qL.8CblGxlG7EMK4cQuxz36OFiOS9WdFpS");
        User user = new User("user", "$2a$12$OTdatQl9ezvtXHHesEH0y.mNvIW/FPbL332PM9dx5/Q4eNLY88fdS");

        userRepository.save(admin);
        userRepository.save(user);

    }
}
