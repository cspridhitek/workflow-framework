package com.ridhitek.workflow.service;

import com.ridhitek.workflow.entity.RolePermission;

public interface RolePermissionService {

    RolePermission assignPermissionToRole(Long roleId, Long permissionId);

    void removePermissionFromRole(Long roleId, Long permissionId);
}