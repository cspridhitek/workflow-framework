package com.ridhitek.workflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowRequestDTO {

    @NotBlank(message = "Workflow name is required")
    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;

}
