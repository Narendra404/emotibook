package com.api.emotibook.serviceImpl;

import com.api.emotibook.model.User;
import com.api.emotibook.repository.UserRepository;
import com.api.emotibook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if(userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User newUser) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User with id : " + id + " is deleted successfully";
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
