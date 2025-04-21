package com.ridhitek.workflow.service.impl;

import java.util.Optional;
import java.util.UUID;

import com.ridhitek.workflow.entity.Role;
import com.ridhitek.workflow.entity.User;
import com.ridhitek.workflow.entity.UserRole; // Import the UserRole entity
import com.ridhitek.workflow.repository.RoleRepository;
import com.ridhitek.workflow.repository.UserRepository;
import com.ridhitek.workflow.repository.UserRoleRepository;
import com.ridhitek.workflow.service.UserRoleService; // Import the missing interface

import org.springframework.stereotype.Service; // Import the Service annotation

@Service
public class UserRoleServiceImpl implements UserRoleService { // Implement the UserRoleService interface

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository,
            RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void assignRoleToUser(UUID userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if (user.isEmpty() || role.isEmpty()) {
            throw new RuntimeException("User or Role not found");
        }
        boolean roleExists = userRoleRepository.existsByUserAndRole(user.get(), role.get());
        if (roleExists) {
            throw new RuntimeException("Role already assigned to user");
        }
        UserRole userRole = new UserRole();
        userRole.setUser(user.get());
        userRole.setRole(role.get());
        userRoleRepository.save(userRole);
    }

    @Override
    public void deleteUserRole(UUID userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));

        Optional<UserRole> userRole = userRoleRepository.findByUserAndRole(user, role);
        userRole.ifPresentOrElse(
                userRoleRepository::delete,
                () -> {
                    throw new RuntimeException("User role not found");
                });
    }

    // @Override
    // public ResponseEntity<?> getUserRole(UUID userId) {
    // User user = userRepository.findById(userId).orElseThrow(() -> new
    // RuntimeException("User not found"));
    // Optional<UserRole> userRole = userRoleRepository.findByUser(user);
    // return userRole.map(ResponseEntity::ok).orElseGet(() ->
    // ResponseEntity.notFound().build());
    // }

    // @Override
    // public ResponseEntity<?> getUserByRole(Long roleId) {
    // Role role = roleRepository.findById(roleId).orElseThrow(() -> new
    // RuntimeException("Role not found"));
    // Optional<UserRole> userRole = userRoleRepository.findByRole(role);
    // return userRole.map(ResponseEntity::ok).orElseGet(() ->
    // ResponseEntity.notFound().build());
    // }

    @Override
    public void updateUserToRole(UUID userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        System.out.println(user.getId());
        Optional<UserRole> userRole = userRoleRepository.findByUser(user);
        if (userRole.isPresent()) {
            userRole.get().setRole(role);
            userRoleRepository.save(userRole.get());
        } else {
            throw new RuntimeException("User role not found");
        }
    }

}
