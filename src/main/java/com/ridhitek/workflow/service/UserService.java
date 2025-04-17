package com.ridhitek.workflow.service;

import com.ridhitek.workflow.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(UUID id);

    User createUser(User user);

    User updateUser(UUID id, User userDetails);

    void deleteUser(UUID id);
}
