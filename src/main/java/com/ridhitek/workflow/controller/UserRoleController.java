package com.ridhitek.workflow.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridhitek.workflow.service.UserRoleService;

@RestController()
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

    // @GetMapping("/get/{userId}/roles")
    // public ResponseEntity<?> getUserRole(@PathVariable UUID userId) {
    // try {
    // return ResponseEntity.ok(userRoleService.getUserRole(userId));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().body("Error fetching user role: " +
    // e.getMessage());
    // }
    // }

    // @GetMapping("/get/roles/{roleId}")
    // public ResponseEntity<?> getUserByRole(@PathVariable Long roleId) {
    // try {
    // return ResponseEntity.ok(userRoleService.getUserByRole(roleId));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().body("Error fetching users by role: " +
    // e.getMessage());
    // }
    // }

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
