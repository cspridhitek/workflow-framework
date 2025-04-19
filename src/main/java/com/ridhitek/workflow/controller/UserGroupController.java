package com.ridhitek.workflow.controller;

import com.ridhitek.workflow.dto.UserGroupDTO;
import com.ridhitek.workflow.service.UserGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user-groups")
public class UserGroupController {

    private final UserGroupService userGroupService;

    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @PostMapping("/assign")
    public String assignUserToGroup(@RequestBody UserGroupDTO userGroupDTO) {
        return userGroupService.assignUserToGroup(userGroupDTO.getUserId(), userGroupDTO.getGroupId());
    }

    @DeleteMapping("/remove")
    public String deleteUserGroup(@RequestParam UUID userId, @RequestParam Long groupId) {
        return userGroupService.deleteUserGroup(userId, groupId);
    }
}