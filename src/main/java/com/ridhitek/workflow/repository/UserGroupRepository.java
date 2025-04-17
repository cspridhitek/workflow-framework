package com.ridhitek.workflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ridhitek.workflow.entity.Group;
import com.ridhitek.workflow.entity.User;
import com.ridhitek.workflow.entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    Optional<UserGroup> findByUserAndGroup(User user, Group group);

}
