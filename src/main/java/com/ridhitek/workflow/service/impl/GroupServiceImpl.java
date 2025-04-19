package com.ridhitek.workflow.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ridhitek.workflow.entity.Group;
import com.ridhitek.workflow.repository.GroupRepository;
import com.ridhitek.workflow.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Override
    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group updateGroup(Long id, Group group) {
        Group existingGroup = groupRepository.findById(id).orElse(null);
        if (existingGroup != null) {
            existingGroup.setName(group.getName()); // Assuming Group has a name field
            existingGroup.setDescription(group.getDescription()); // Assuming Group has a description field
            return groupRepository.save(existingGroup);
        }
        return null;
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
