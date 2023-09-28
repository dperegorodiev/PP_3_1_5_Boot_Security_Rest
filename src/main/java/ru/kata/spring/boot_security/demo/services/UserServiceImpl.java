package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User readUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }


    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new  org.springframework.security.core.userdetails.User( user.getUsername(),user.getPassword(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isEnabled(), user.isAccountNonLocked(),
                user.getRoles());
    }
}
