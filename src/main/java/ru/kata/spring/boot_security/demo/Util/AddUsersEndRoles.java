package ru.kata.spring.boot_security.demo.Util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;
import java.util.List;
import java.util.Set;
@Component
public class AddUsersEndRoles implements ApplicationRunner {
    private final RoleRepository roleRepository;
    private final UserService userService;
    public AddUsersEndRoles(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.saveAll(List.of(userRole, adminRole));

        User user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setAge(32);
        user.setPassword("user");
        user.setEmail("user@mail.ru");
        user.setRoles(Set.of(userRole));

        User admin = new User();
        admin.setFirstName("Dima");
        admin.setLastName("Peregorodiev");
        admin.setAge(33);
        admin.setPassword("admin");
        admin.setEmail("admin@mail.ru");
        admin.setRoles(Set.of(adminRole, userRole));

        userService.saveUser(user);
        userService.saveUser(admin);
    }
}
