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
    public String assignRoleToUser(UUID userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        boolean roleExists = userRoleRepository.existsByUserAndRole(user, role);
        if (roleExists) {
            throw new RuntimeException("Role already assigned to user");
        }
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
        return "Role" + role.getName() + " assigned to user " + user.getUsername();
    }

    @Override
    public String deleteUserRole(UUID userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        Optional<UserRole> userRole = userRoleRepository.findByUserAndRole(user, role); // Assuming you have this method
                                                                                        // in your repository
        userRole.ifPresentOrElse(
                userRoleRepository::delete,
                () -> {
                    throw new RuntimeException("User role not found");
                });
        return "User role deleted successfully";
    }
}
