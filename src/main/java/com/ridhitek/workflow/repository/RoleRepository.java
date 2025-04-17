package com.ridhitek.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridhitek.workflow.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Custom query methods can be defined here if needed
    
}
