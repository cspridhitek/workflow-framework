package com.ridhitek.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ridhitek.workflow.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
