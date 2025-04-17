package com.ridhitek.workflow.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ridhitek.workflow.entity.Permission;
import com.ridhitek.workflow.repository.PermissionRepository;
import com.ridhitek.workflow.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found"));
    }

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Long id, Permission permission) {
        Permission existingPermission = getPermissionById(id);
        existingPermission.setName(permission.getName()); // Assuming Permission has a 'name' field
        return permissionRepository.save(existingPermission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
