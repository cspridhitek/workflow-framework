package com.ridhitek.workflow.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.ridhitek.workflow.entity.Role;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role createRole(Role role) throws DataIntegrityViolationException, Exception;

    Role updateRole(Long id, Role roleDetails);

    void deleteRole(Long id);
}
