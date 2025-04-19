package com.ridhitek.workflow.service;

import com.ridhitek.workflow.entity.RolePermission;
import java.util.List;

public interface RolePermissionService {
    RolePermission createRolePermission(RolePermission rolePermission);

    void deleteRolePermission(Long id);

    List<RolePermission> getAllRolePermissions();

    RolePermission assignPermissionToRole(Long roleId, Long permissionId);

    void removePermissionFromRole(Long roleId, Long permissionId);
}