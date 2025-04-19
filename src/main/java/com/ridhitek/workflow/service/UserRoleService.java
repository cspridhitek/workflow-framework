package com.ridhitek.workflow.service;

import java.util.UUID;

import com.ridhitek.workflow.dto.UserRoleDTO;

public interface UserRoleService {
    public void assignRoleToUser(UserRoleDTO userRoleDTO);

    public void updateUserRole(UserRoleDTO userRoleDTO);

    public void deleteUserRole(UUID userId, Long roleId);
}
