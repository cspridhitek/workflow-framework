package com.ridhitek.workflow.service;

import java.util.UUID;

public interface UserGroupService {

    public String assignUserToGroup(UUID userId, Long groupId);

    public String deleteUserGroup(UUID userId, Long groupId);
}