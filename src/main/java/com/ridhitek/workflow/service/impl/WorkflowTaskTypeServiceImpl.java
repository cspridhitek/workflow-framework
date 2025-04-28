package com.ridhitek.workflow.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ridhitek.workflow.dto.WorkflowTaskTypeDTO;
import com.ridhitek.workflow.entity.WorkflowTaskType;
import com.ridhitek.workflow.repository.WorkflowStepRepository;
import com.ridhitek.workflow.repository.WorkflowTaskTypeRepository;
import com.ridhitek.workflow.service.WorkflowTaskTypeService;

@Service
public class WorkflowTaskTypeServiceImpl implements WorkflowTaskTypeService {

    private final WorkflowTaskTypeRepository workflowTaskTypeRepository;
    private final WorkflowStepRepository workflowStepRepository;

    public WorkflowTaskTypeServiceImpl(WorkflowTaskTypeRepository workflowTaskTypeRepository,
            WorkflowStepRepository workflowStepRepository) {
        this.workflowTaskTypeRepository = workflowTaskTypeRepository;
        this.workflowStepRepository = workflowStepRepository;
    }

    @Override
    public WorkflowTaskTypeDTO create(WorkflowTaskTypeDTO dto) {
        WorkflowTaskType taskType = new WorkflowTaskType();
        taskType.setStep(workflowStepRepository.findById(dto.getWorkflowStepId())
                .orElseThrow(() -> new RuntimeException("Workflow Step not found")));
        taskType.setTaskType(dto.getTaskType());

        WorkflowTaskType saved = workflowTaskTypeRepository.save(taskType);
        return mapToDTO(saved);
    }

    @Override
    public WorkflowTaskTypeDTO getById(Long id) {
        WorkflowTaskType taskType = workflowTaskTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task Type not found"));
        return mapToDTO(taskType);
    }

    @Override
    public WorkflowTaskTypeDTO getByStepId(Long stepId) {
        WorkflowTaskType taskType = workflowTaskTypeRepository.findByStepId(stepId)
                .orElseThrow(() -> new RuntimeException("Task Type for Step not found"));
        return mapToDTO(taskType);
    }

    @Override
    public List<WorkflowTaskTypeDTO> getAll() {
        return workflowTaskTypeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WorkflowTaskTypeDTO update(Long id, WorkflowTaskTypeDTO dto) {
        WorkflowTaskType taskType = workflowTaskTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task Type not found"));
        taskType.setStep(workflowStepRepository.findById(dto.getWorkflowStepId())
                .orElseThrow(() -> new RuntimeException("Workflow Step not found")));
        taskType.setTaskType(dto.getTaskType());

        WorkflowTaskType updated = workflowTaskTypeRepository.save(taskType);
        return mapToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        workflowTaskTypeRepository.deleteById(id);
    }

    private WorkflowTaskTypeDTO mapToDTO(WorkflowTaskType entity) {
        WorkflowTaskTypeDTO dto = new WorkflowTaskTypeDTO();
        dto.setId(entity.getId());
        dto.setWorkflowStepId(entity.getStep().getId());
        dto.setTaskType(entity.getTaskType());
        return dto;
    }
}
