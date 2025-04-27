package com.ridhitek.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowTaskTypeDTO {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long workflowStepId;
    private String taskType;

}
