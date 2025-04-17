package com.ridhitek.workflow.service;

import java.util.List;
import com.ridhitek.workflow.entity.Permission;

public interface PermissionService {
    List<Permission> getAllPermissions();
    Permission getPermissionById(Long id);
    Permission createPermission(Permission permission);
    Permission updatePermission(Long id, Permission permission);
    void deletePermission(Long id);
}
