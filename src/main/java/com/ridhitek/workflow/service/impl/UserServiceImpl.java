package com.ridhitek.workflow.service.impl;

import com.ridhitek.workflow.entity.User;
import com.ridhitek.workflow.repository.UserRepository;
import com.ridhitek.workflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        logger.info("Creating user: " + user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UUID id, Map<String, String> userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (userDetails.containsKey("username")) {
            user.setUsername(userDetails.get("username"));
        }
        if (userDetails.containsKey("email")) {
            user.setEmail(userDetails.get("email"));
        }
        if (userDetails.containsKey("status")) {
            user.setStatus(userDetails.get("status"));
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(UUID id) {
        logger.info("Deleting user with ID: " + id);
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

}
