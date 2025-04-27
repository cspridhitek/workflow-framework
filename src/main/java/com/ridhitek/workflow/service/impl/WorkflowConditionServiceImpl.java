package com.ridhitek.workflow.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ridhitek.workflow.dto.WorkflowConditionDTO;
import com.ridhitek.workflow.entity.WorkflowCondition;
import com.ridhitek.workflow.repository.WorkflowConditionRepository;
import com.ridhitek.workflow.repository.WorkflowStepRepository;
import com.ridhitek.workflow.service.WorkflowConditionService;

@Service
public class WorkflowConditionServiceImpl implements WorkflowConditionService {

    private final WorkflowConditionRepository workflowConditionRepository;
    private final WorkflowStepRepository workflowStepRepository;

    public WorkflowConditionServiceImpl(WorkflowConditionRepository workflowConditionRepository,
            WorkflowStepRepository workflowStepRepository) {
        this.workflowConditionRepository = workflowConditionRepository;
        this.workflowStepRepository = workflowStepRepository;
    }

    @Override
    public WorkflowConditionDTO create(WorkflowConditionDTO dto) {
        WorkflowCondition condition = new WorkflowCondition();
        condition.setStepFrom(workflowStepRepository.findById(dto.getStepFromId())
                .orElseThrow(() -> new RuntimeException("Step From not found")));
        condition.setStepTo(workflowStepRepository.findById(dto.getStepToId())
                .orElseThrow(() -> new RuntimeException("Step To not found")));
        condition.setConditionType(dto.getConditionType());
        condition.setConditionValue(dto.getConditionValue());

        WorkflowCondition saved = workflowConditionRepository.save(condition);
        return mapToDTO(saved);
    }

    @Override
    public List<WorkflowConditionDTO> getAll() {
        return workflowConditionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WorkflowConditionDTO getById(Long id) {
        WorkflowCondition condition = workflowConditionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow Condition not found"));
        return mapToDTO(condition);
    }

    @Override
    public WorkflowConditionDTO update(Long id, WorkflowConditionDTO dto) {
        WorkflowCondition condition = workflowConditionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow Condition not found"));

        condition.setStepFrom(workflowStepRepository.findById(dto.getStepFromId())
                .orElseThrow(() -> new RuntimeException("Step From not found")));
        condition.setStepTo(workflowStepRepository.findById(dto.getStepToId())
                .orElseThrow(() -> new RuntimeException("Step To not found")));
        condition.setConditionType(dto.getConditionType());
        condition.setConditionValue(dto.getConditionValue());

        WorkflowCondition updated = workflowConditionRepository.save(condition);
        return mapToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        workflowConditionRepository.deleteById(id);
    }

    @Override
    public List<WorkflowConditionDTO> findByStepFrom(Long stepFromId) {
        List<WorkflowCondition> conditions = workflowConditionRepository.findByStepFromId(stepFromId);
        return conditions.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private WorkflowConditionDTO mapToDTO(WorkflowCondition condition) {
        WorkflowConditionDTO dto = new WorkflowConditionDTO();
        dto.setId(condition.getId());
        dto.setStepFromId(condition.getStepFrom().getId());
        dto.setStepToId(condition.getStepTo().getId());
        dto.setConditionType(condition.getConditionType());
        dto.setConditionValue(condition.getConditionValue());
        return dto;
    }
}
