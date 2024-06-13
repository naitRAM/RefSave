package dev.ramimans.refsave.controller;

import dev.ramimans.refsave.dao.UserDaoImpl;
import dev.ramimans.refsave.dao.mapper.UserDao;
import dev.ramimans.refsave.dto.User;
import dev.ramimans.refsave.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;



@RestController
public class UserController {
    @Autowired
    UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{username}")
    public ResponseEntity<User> readUser(@PathVariable String username) {
        return new ResponseEntity<User>(userService.readUser(username), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@Valid @RequestBody  User user) {
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        userService.createUser(user);
        return new ResponseEntity<String>(userId, HttpStatus.CREATED);
    }
}