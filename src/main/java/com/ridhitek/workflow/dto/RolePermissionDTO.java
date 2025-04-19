package com.ridhitek.workflow.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long roleId;
    private Long permissionId;
}