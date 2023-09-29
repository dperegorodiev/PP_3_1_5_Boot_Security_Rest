package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

@Component
public class RoleAdd implements ApplicationRunner {

    private final RoleRepository roleRepository;

    public RoleAdd(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Role admin = new Role("ROLE_ADMIN");
        Role users = new Role ("ROLE_USER");

        roleRepository.save(admin);
        roleRepository.save(users);
    }
}
