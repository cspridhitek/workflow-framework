package com.ridhitek.workflow.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkflowRuleDTO {

    private Long id;
    private Long stepId;
    private Long roleId;
    private Long groupId;
    private UUID userId;

}
