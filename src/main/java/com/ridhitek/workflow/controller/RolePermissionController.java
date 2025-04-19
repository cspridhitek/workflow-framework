package com.ridhitek.workflow.controller;

import com.ridhitek.workflow.dto.RolePermissionDTO;
import com.ridhitek.workflow.entity.RolePermission;
import com.ridhitek.workflow.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    @Autowired
    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    // Assign a permission to a role
    @PostMapping("/assign")
    public ResponseEntity<RolePermission> assignPermissionToRole(@RequestBody RolePermissionDTO rolePermissionDTO) {
        RolePermission rolePermission = rolePermissionService.assignPermissionToRole(
                rolePermissionDTO.getRoleId(), rolePermissionDTO.getPermissionId());
        return ResponseEntity.ok(rolePermission);
    }

    // Remove a permission from a role
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removePermissionFromRole(@RequestBody RolePermissionDTO rolePermissionDTO) {
        rolePermissionService.removePermissionFromRole(
                rolePermissionDTO.getRoleId(), rolePermissionDTO.getPermissionId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public RolePermission createRolePermission(@RequestBody RolePermissionDTO rolePermissionDTO) {
        RolePermission rolePermission = new RolePermission();
        return rolePermissionService.createRolePermission(rolePermission);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRolePermission(@PathVariable Long id) {
        rolePermissionService.deleteRolePermission(id);
    }

    @GetMapping("/all")
    public List<RolePermission> getAllRolePermissions() {
        return rolePermissionService.getAllRolePermissions();
    }
}