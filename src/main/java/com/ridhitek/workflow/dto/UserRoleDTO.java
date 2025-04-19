package com.ridhitek.workflow.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID userId;
    private Long roleId;

}
