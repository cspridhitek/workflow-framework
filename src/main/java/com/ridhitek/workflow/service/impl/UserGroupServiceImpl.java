package com.ridhitek.workflow.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ridhitek.workflow.entity.Group;
import com.ridhitek.workflow.entity.User;
import com.ridhitek.workflow.entity.UserGroup;
import com.ridhitek.workflow.repository.GroupRepository;
import com.ridhitek.workflow.repository.UserGroupRepository;
import com.ridhitek.workflow.repository.UserRepository;
import com.ridhitek.workflow.service.UserGroupService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserGroupServiceImpl implements UserGroupService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;

    @Override
    public String assignUserToGroup(UUID userId, Long groupId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Optional<UserGroup> existingMapping = userGroupRepository.findByUserAndGroup(user, group);
        if (existingMapping.isPresent()) {
            return "Group already assigned to user.";
        }

        UserGroup userGroup = new UserGroup();
        userGroup.setUser(user);
        userGroup.setGroup(group);

        userGroupRepository.save(userGroup);
        return "Group assigned to user successfully.";
    }

    @Override
    public String deleteUserGroup(UUID userId, Long groupId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Optional<UserGroup> userGroup = userGroupRepository.findByUserAndGroup(user, group);
        if (userGroup.isPresent()) {
            userGroupRepository.delete(userGroup.get());
            return "User removed from group successfully.";
        } else {
            throw new RuntimeException("User is not part of the group.");
        }
    }
}
