package com.ridhitek.workflow.service.impl;

import java.util.Optional;
import java.util.UUID;

import com.ridhitek.workflow.dto.UserRoleDTO;
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
    public void assignRoleToUser(UserRoleDTO userRoleDTO) {
        UUID userId = userRoleDTO.getUserId(); // Assuming you have a method to get the user ID from the DTO
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(userRoleDTO.getRoleId());
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
        Optional<UserRole> userRole = userRoleRepository.findByUserAndRole(user, role); // Assuming you have this method
                                                                                        // in your repository
        userRole.ifPresentOrElse(
                userRoleRepository::delete,
                () -> {
                    throw new RuntimeException("User role not found");
                });
    }

    @Override
    public void updateUserRole(UserRoleDTO userRoleDTO) {
        UUID userId = userRoleDTO.getUserId();
        Long newRoleId = userRoleDTO.getRoleId();

        // Fetch the user and the new role
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role newRole = roleRepository.findById(newRoleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Check if the user already has the new role
        boolean roleExists = userRoleRepository.existsByUserAndRole(user, newRole);
        if (roleExists) {
            throw new RuntimeException("User already has the specified role");
        }

        // Fetch the current UserRole and update it
        Optional<UserRole> currentUserRole = userRoleRepository.findByUser(user);
        currentUserRole.ifPresentOrElse(
                userRole -> {
                    userRole.setRole(newRole);
                    userRoleRepository.save(userRole);
                },
                () -> {
                    throw new RuntimeException("User does not have an existing role to update");
                });
    }
}
