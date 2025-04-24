package com.ridhitek.workflow.service.impl;

import com.ridhitek.workflow.dto.WorkflowRequestDTO;
import com.ridhitek.workflow.dto.WorkflowResponseDTO;
import com.ridhitek.workflow.entity.Workflow;
import com.ridhitek.workflow.exception.WorkflowNotFoundException;
import com.ridhitek.workflow.mapper.WorkflowMapper;
import com.ridhitek.workflow.repository.WorkflowRepository;
import com.ridhitek.workflow.service.WorkflowService;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkFlowServiceImpl implements WorkflowService {

    private final WorkflowRepository workflowRepository;

    public WorkFlowServiceImpl(WorkflowRepository workflowRepository) {
        this.workflowRepository = workflowRepository;
    }


    @Override
    public WorkflowResponseDTO createWorkflow(WorkflowRequestDTO workflowRequestDTO) {
        Workflow workflow = workflowRepository.save(WorkflowMapper.toEntity(workflowRequestDTO));
        return WorkflowMapper.toResponseDTO(workflow);
    }

    @Override
    public List<WorkflowResponseDTO> getAllWorkflows() {
        return workflowRepository.findAll().stream().map(WorkflowMapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public WorkflowResponseDTO getWorkflowById(UUID id) {
        Workflow workflow = workflowRepository.findById(id).orElseThrow(() -> new WorkflowNotFoundException("Workflow not found with id: " + id));
        return WorkflowMapper.toResponseDTO(workflow);
    }

    @Override
    public WorkflowResponseDTO updateWorkflow(UUID id, WorkflowRequestDTO updatedWorkflow) {
        WorkflowResponseDTO existing = getWorkflowById(id);
        existing.setName(updatedWorkflow.getName());
        existing.setDescription(updatedWorkflow.getDescription());
        return WorkflowMapper.toResponseDTO(workflowRepository.save(WorkflowMapper.toResponseEntity(existing)));
    }

    @Override
    public void deleteWorkflow(UUID id) {
        WorkflowResponseDTO existing = getWorkflowById(id);
        if (existing != null) {
            workflowRepository.deleteById(id);
        }

    }
}

