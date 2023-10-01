package ru.kata.spring.boot_security.demo.services;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Set;

@Component
public class UserAdd implements ApplicationRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserAdd (UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role admin = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        Set<Role> adminList = Set.of(admin);
        Set<Role> userList = Set.of(userRole);

        roleRepository.save(admin);
        roleRepository.save(userRole);

        User adminUser = new User();
        adminUser.setRoles(adminList);
        adminUser.setPassword(passwordEncoder.encode("$2a$12$cUtcTNTxeqBmJA40rp7qL.8CblGxlG7EMK4cQuxz36OFiOS9WdFpS"));
        adminUser.setAge(33);
        adminUser.setName("Dima");
        adminUser.setSurname("Peregororodiev");
        adminUser.setUsername("admin");
        userRepository.saveAndFlush(adminUser);

        User user = new User();
        user.setRoles(userList);
        user.setPassword(passwordEncoder.encode("$2a$12$OTdatQl9ezvtXHHesEH0y.mNvIW/FPbL332PM9dx5/Q4eNLY88fdS"));
        user.setAge(25);
        user.setName("Inav");
        user.setSurname("Ivamov");
        user.setUsername("user");
        userRepository.saveAndFlush(user);
    }
}
