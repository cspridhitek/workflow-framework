package com.ridhitek.workflow.repository;

import com.ridhitek.workflow.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    Optional<RolePermission> findByRoleIdAndPermissionId(Long roleId, Long permissionId);
}