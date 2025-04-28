package com.ridhitek.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowSlaDTO {

    private Long id;
    private Long workflowStepId;
    private Integer expectedCompletionTimeMins;
}
