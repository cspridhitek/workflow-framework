package com.ridhitek.workflow.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ridhitek.workflow.service.UserRoleService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user-role")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/assign/{userId}/roles")
    public ResponseEntity<?> assignRoleToUser(@PathVariable UUID userId, @RequestParam Long roleId) {
        try {
            System.out.println("Assigning role " + roleId + " to user " + userId);
            userRoleService.assignRoleToUser(userId, roleId);
            return ResponseEntity.ok("Role assigned to user successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error assigning role to user: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{userId}/roles")
    public ResponseEntity<?> deleteUserRole(@PathVariable UUID userId, @RequestParam Long roleId) {
        try {
            userRoleService.deleteUserRole(userId, roleId);
            return ResponseEntity.ok("Role deleted from user successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting role from user: " + e.getMessage());
        }
    }

    @PutMapping("/update/{userId}/roles")
    public ResponseEntity<?> updateUserToRole(@PathVariable UUID userId, @RequestParam Long roleId) {
        try {
            userRoleService.updateUserToRole(userId, roleId);
            return ResponseEntity.ok("User updated to role successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating user to role: " + e.getMessage());
        }
    }
}
