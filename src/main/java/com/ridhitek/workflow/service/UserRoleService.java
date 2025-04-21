package com.ridhitek.workflow.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

public interface UserRoleService {
    public void assignRoleToUser(UUID userId, Long roleId);

    public void deleteUserRole(UUID userId, Long roleId);

    // public ResponseEntity<?> getUserRole(UUID userId);

    // public ResponseEntity<?> getUserByRole(Long roleId);

    public void updateUserToRole(UUID userId, Long roleId);
}
