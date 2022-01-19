package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exeption_handing.NoSuchUserException;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class RESTAdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RESTAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) {
            throw new NoSuchUserException(String.format("User with id-'%s' not found in database", id));
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return ResponseEntity.ok(user);
    }

    @PatchMapping()
    public ResponseEntity<User> updatePatchUser(@RequestBody User user) {
        userService.saveEditUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
