package com.ridhitek.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkflowConditionDTO {
    private Long id;
    private Long stepFromId;
    private Long stepToId;
    private String conditionType;
    private String conditionValue;
}
