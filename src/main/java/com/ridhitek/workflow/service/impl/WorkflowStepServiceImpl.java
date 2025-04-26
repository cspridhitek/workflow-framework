package com.ridhitek.workflow.service.impl;

import com.ridhitek.workflow.dto.WorkflowStepDTO;
import com.ridhitek.workflow.entity.Workflow;
import com.ridhitek.workflow.entity.WorkflowStep;
import com.ridhitek.workflow.repository.WorkflowRepository;
import com.ridhitek.workflow.repository.WorkflowStepRepository;
import com.ridhitek.workflow.service.WorkflowStepService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WorkflowStepServiceImpl implements WorkflowStepService {

    private final WorkflowStepRepository workflowStepRepository;
    private final WorkflowRepository workflowRepository;

    public WorkflowStepServiceImpl(WorkflowStepRepository workflowStepRepository) {
        this.workflowStepRepository = workflowStepRepository;
        this.workflowRepository = null;
    }

    @Override
    public WorkflowStepDTO getWorkflowStepById(Long id) {
        WorkflowStep workflowStep = workflowStepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow step not found"));
        return mapToDTO(workflowStep);
    }

    private WorkflowStepDTO mapToDTO(WorkflowStep workflowStep) {
        WorkflowStepDTO workflowStepDTO = WorkflowStepDTO.builder()
                .id(workflowStep.getId())
                .name(workflowStep.getName())
                .stepOrder(workflowStep.getStepOrder())
                .isMandatory(workflowStep.getIsMandatory())
                .workflowId(workflowStep.getWorkflow().getId())
                .build();
        // Set other properties as needed
        return workflowStepDTO;
    }

    @Override
    public WorkflowStepDTO createWorkflowStep(WorkflowStepDTO workflowStepDTO) {
        Workflow workflow = workflowRepository.findById(workflowStepDTO.getWorkflowId())
                .orElseThrow(() -> new RuntimeException("Workflow not found"));
        WorkflowStep workflowStep = WorkflowStep.builder()
                .name(workflowStepDTO.getName())
                .stepOrder(workflowStepDTO.getStepOrder())
                .isMandatory(workflowStepDTO.getIsMandatory())
                .workflow(workflow)
                .build();
        return mapToDTO(workflowStepRepository.save(workflowStep));
    }

    @Override
    public WorkflowStepDTO updateWorkflowStep(Long id, WorkflowStepDTO workflowStepDTO) {
        WorkflowStep workflowStep = workflowStepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow step not found"));
        Workflow workflow = workflowRepository.findById(workflowStepDTO.getWorkflowId())
                .orElseThrow(() -> new RuntimeException("Workflow not found"));
        workflowStep.setWorkflow(workflow);
        workflowStep.setId(id);
        workflowStep.setName(workflowStepDTO.getName());
        workflowStep.setStepOrder(workflowStepDTO.getStepOrder());
        workflowStep.setIsMandatory(workflowStepDTO.getIsMandatory());
        // Update other properties as needed
        workflowStep = workflowStepRepository.save(workflowStep);
        return mapToDTO(workflowStep);

    }

    @Override
    public List<WorkflowStepDTO> getWorkflowStepsByWorkflowId(UUID workflowId) {
        List<WorkflowStep> workflowSteps = workflowStepRepository.findByWorkflow_Id(workflowId);
        return workflowSteps.stream().map(this::mapToDTO).toList();
    }

    @Override
    public void deleteWorkflowStep(UUID workflowId) {
        workflowStepRepository.deleteByWorkflow_Id(workflowId);
    }

    @Override
    public void deleteWorkflowStepById(UUID workflowId, Integer stepId) {
        List<WorkflowStep> workflowStep = workflowStepRepository.findByWorkflow_Id(workflowId);
        Optional<WorkflowStep> workflowStepOptional = workflowStep.stream()
                .filter(step -> step.getId().equals(stepId)).findFirst();
        if (workflowStepOptional.isPresent()) {
            workflowStepRepository.delete(workflowStepOptional.get());
        } else {
            throw new RuntimeException("Workflow step not found");
        }
    }
}
