package com.api.emotibook.controller;

import com.api.emotibook.exception.UserNotFoundException;
import com.api.emotibook.model.User;
import com.api.emotibook.serviceImpl.GeminiService;
import com.api.emotibook.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final GeminiService geminiService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, GeminiService geminiService) {
        this.userServiceImpl = userServiceImpl;
        this.geminiService = geminiService;
    }

    @PostMapping("/user")
    User newUser(@RequestBody User user) {
        return userServiceImpl.createUser(user);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userServiceImpl.getUserById(id);
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userServiceImpl.updateUser(id, newUser);
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        return userServiceImpl.deleteUser(id);
    }

    @GetMapping("/userlogin")
    User login(
            @RequestParam String email,
            @RequestParam String password) throws UserNotFoundException {

        User user = userServiceImpl.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new UserNotFoundException(user != null ? user.getId() : -1);
        }

    }

    @PostMapping("user/gemini")
    String getGemini(@RequestBody String text) {
        return geminiService.generateContent(text);
    }
}
