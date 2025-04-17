package com.ridhitek.workflow.controller;

import com.ridhitek.workflow.entity.RolePermission;
import com.ridhitek.workflow.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    // Assign a permission to a role
    @PostMapping("/assign")
    public ResponseEntity<RolePermission> assignPermissionToRole(@RequestParam Long roleId,
            @RequestParam Long permissionId) {
        RolePermission rolePermission = rolePermissionService.assignPermissionToRole(roleId, permissionId);
        return ResponseEntity.ok(rolePermission);
    }

    // Remove a permission from a role
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removePermissionFromRole(@RequestParam Long roleId, @RequestParam Long permissionId) {
        rolePermissionService.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.noContent().build();
    }
}