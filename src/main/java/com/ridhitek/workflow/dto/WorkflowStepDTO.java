package com.ridhitek.workflow.dto;

import java.util.UUID;

import org.apache.kafka.common.protocol.types.Field.Bool;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowStepDTO {

    private Long id;
    @NotBlank(message = "Step name is required")
    private String name;

    @NotBlank(message = "Step order is required")
    private Integer stepOrder;
    private Boolean isMandatory;

    @NotBlank(message = "Workflow ID is required")
    private UUID workflowId;

}
