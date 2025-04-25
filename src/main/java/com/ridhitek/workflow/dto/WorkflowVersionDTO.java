package com.ridhitek.workflow.dto;


import com.ridhitek.workflow.entity.WorkflowVersion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowVersionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String version;
    private String workflowId;
    private String description;

    public WorkflowVersionDTO(WorkflowVersion workflowVersion) {
        this.version = workflowVersion.getVersion();
        this.workflowId = workflowVersion.getWorkflow().getId().toString();
        this.description = workflowVersion.getDescription();
    }
}
