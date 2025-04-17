package com.ridhitek.workflow.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridhitek.workflow.service.UserRoleService;


@RestController("/api/user-roles")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/assign")
    public String assignRoleToUser(@RequestParam UUID userId, @RequestParam Long roleId) {
        return userRoleService.assignRoleToUser(userId, roleId);
    }

    @DeleteMapping("/delete")
    public String deleteUserRole(@RequestParam UUID userId, @RequestParam Long roleId) {
        userRoleService.deleteUserRole(userId, roleId);
        return "User role deleted successfully";

    }
       
}
