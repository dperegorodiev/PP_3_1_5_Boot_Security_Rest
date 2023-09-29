package ru.kata.spring.boot_security.demo.services;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

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
        Role user = new Role("ROLE_USER");

//        User admin = new User("admin", "$2a$12$cUtcTNTxeqBmJA40rp7qL.8CblGxlG7EMK4cQuxz36OFiOS9WdFpS",
//                "Dima", "Peregorodiev", 33);
//        User User = new User("user", "$2a$12$OTdatQl9ezvtXHHesEH0y.mNvIW/FPbL332PM9dx5/Q4eNLY88fdS",
//                "Ivan", "Ivanov", 18);

        Set<Role> adminList = Set.of(admin);
//        List<User>userList = List.of(user);

        roleRepository.save(admin);
        roleRepository.save(user);

        User adminUser = new User();
        adminUser.setRoles(adminList);
        adminUser.setPassword(passwordEncoder.encode("$2a$12$OTdatQl9ezvtXHHesEH0y.mNvIW/FPbL332PM9dx5/Q4eNLY88fdS"));
        adminUser.setAge(30);
        adminUser.setName("Ivan");
        adminUser.setSurname("Ivanov");
        adminUser.setUsername("user");
        userRepository.saveAndFlush(adminUser);
    }
}
