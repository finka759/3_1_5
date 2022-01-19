package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class initDB {
    private final UserService userService;

    @Autowired
    public initDB(UserServiceImp userServiceImp) {
        this.userService = userServiceImp;
    }

    @PostConstruct
    public void init() {
        createTwoUsers();
    }

    public void createTwoUsers() {
        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        roleAdmin.add(role2);
        roleAdmin.add(role1);
        roleUser.add(role2);
        User user1 = new User("em_u1", "fn_u1", "ln_u1", (short) 11, "111", roleAdmin);
        User user2 = new User("em_u2", "fn_u2", "ln_u2", (short) 22, "111", roleUser);
        userService.saveNewUser(user1);
        userService.saveNewUser(user2);
    }
}
