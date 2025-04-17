package com.ridhitek.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ridhitek.workflow.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
