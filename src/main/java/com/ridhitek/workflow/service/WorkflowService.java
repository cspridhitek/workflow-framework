package com.ridhitek.workflow.service;

import com.ridhitek.workflow.dto.WorkflowRequestDTO;
import com.ridhitek.workflow.dto.WorkflowResponseDTO;

import java.util.List;
import java.util.UUID;

public interface WorkflowService {
    WorkflowResponseDTO createWorkflow(WorkflowRequestDTO workflowRequestDTO);

    List<WorkflowResponseDTO> getAllWorkflows();

    WorkflowResponseDTO getWorkflowById(UUID id);

    WorkflowResponseDTO updateWorkflow(UUID id, WorkflowRequestDTO workflowRequestDTO);

    void deleteWorkflow(UUID id);

}
