package com.ridhitek.workflow.service.impl;

import com.ridhitek.workflow.entity.Role;
import com.ridhitek.workflow.entity.Permission;
import com.ridhitek.workflow.entity.RolePermission;
import com.ridhitek.workflow.repository.RolePermissionRepository;
import com.ridhitek.workflow.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public RolePermission assignPermissionToRole(Long roleId, Long permissionId) {
        // Check if the RolePermission already exists
        Optional<RolePermission> existingRolePermission = rolePermissionRepository.findByRoleIdAndPermissionId(roleId,
                permissionId);
        if (existingRolePermission.isPresent()) {
            return existingRolePermission.get();
        }

        // Create a new RolePermission
        RolePermission rolePermission = new RolePermission();
        Role role = new Role();
        role.setId(roleId);
        Permission permission = new Permission();
        permission.setId(permissionId);

        rolePermission.setRole(role);
        rolePermission.setPermission(permission);

        // Save the RolePermission
        return rolePermissionRepository.save(rolePermission);
    }

    @Override
    public void removePermissionFromRole(Long roleId, Long permissionId) {
        // Find the RolePermission by roleId and permissionId
        Optional<RolePermission> rolePermission = rolePermissionRepository.findByRoleIdAndPermissionId(roleId,
                permissionId);

        // If the RolePermission exists, delete it
        rolePermission.ifPresent(rolePermissionRepository::delete);
    }
}