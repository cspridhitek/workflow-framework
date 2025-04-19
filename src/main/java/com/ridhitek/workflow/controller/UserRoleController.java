package com.ridhitek.workflow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.ridhitek.workflow.dto.UserRoleDTO;
import com.ridhitek.workflow.service.UserRoleService;

import jakarta.validation.Valid;

@RestController("/api/user-roles")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignRoleToUser(@RequestBody UserRoleDTO userRoleDTO) {
        try {
            userRoleService.assignRoleToUser(userRoleDTO);
            return ResponseEntity.ok("Role assigned to user successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error assigning role to user: " + e.getMessage());
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateUserRole(@RequestBody @Valid UserRoleDTO userRoleDTO) {
        try {
            userRoleService.updateUserRole(userRoleDTO); // Assuming this method can also handle updates
            return ResponseEntity.ok("User role updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating user role: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUserRole(@RequestBody @Valid UserRoleDTO userRoleDTO) {
        try {
            userRoleService.deleteUserRole(userRoleDTO.getUserId(), userRoleDTO.getRoleId());
            return ResponseEntity.ok("User role deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting user role: " + e.getMessage());
        }
    }
}
