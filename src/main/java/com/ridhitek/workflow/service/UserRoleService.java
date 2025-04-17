package com.ridhitek.workflow.service;

import java.util.UUID;

public interface UserRoleService {
    public String assignRoleToUser(UUID userId, Long roleId);

    public String deleteUserRole(UUID userId, Long roleId);
}
