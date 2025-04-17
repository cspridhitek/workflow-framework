package com.ridhitek.workflow.service;

import java.util.List;
import com.ridhitek.workflow.entity.Group;

public interface GroupService {
    List<Group> getAllGroups();
    Group getGroupById(Long id);
    Group createGroup(Group group);
    Group updateGroup(Long id, Group group);
    void deleteGroup(Long id);
}
