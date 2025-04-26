package com.ridhitek.workflow.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.ridhitek.workflow.dto.WorkflowStepDTO;

public interface WorkflowStepService {

    WorkflowStepDTO createWorkflowStep(WorkflowStepDTO workflowStepDTO);

    WorkflowStepDTO updateWorkflowStep(Long id, WorkflowStepDTO workflowStepDTO);

    Object getWorkflowStepsByWorkflowId(UUID workflowId);

    void deleteWorkflowStep(UUID workflowId);

    void deleteWorkflowStepById(UUID workflowId, Integer stepId);

    WorkflowStepDTO getWorkflowStepById(Long id);

}
