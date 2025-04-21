package com.ridhitek.workflow.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ridhitek.workflow.entity.Role;
import com.ridhitek.workflow.entity.User;
import com.ridhitek.workflow.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    boolean existsByUserAndRole(User user, Role role);

    Optional<UserRole> findByUserAndRole(User user, Role role);

    Optional<UserRole> findByUser(User user);

    Optional<User> findByUserIdAndRoleId(UUID userId, Long roleId);

    Optional<UserRole> findByRole(Role role);

}
