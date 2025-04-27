package com.ridhitek.workflow.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ridhitek.workflow.dto.WorkflowRuleDTO;
import com.ridhitek.workflow.entity.WorkflowRule;
import com.ridhitek.workflow.repository.WorkflowRuleRepository;
import com.ridhitek.workflow.repository.WorkflowStepRepository;
import com.ridhitek.workflow.service.WorkflowRuleService;

@Service
public class WorkflowRuleServiceImpl implements WorkflowRuleService {

    private final WorkflowRuleRepository workflowRuleRepository;
    private final WorkflowStepRepository workflowStepRepository;

    public WorkflowRuleServiceImpl(WorkflowRuleRepository workflowRuleRepository,
            WorkflowStepRepository workflowStepRepository) {
        this.workflowRuleRepository = workflowRuleRepository;
        this.workflowStepRepository = workflowStepRepository;
    }

    @Override
    public WorkflowRuleDTO create(WorkflowRuleDTO dto) {
        WorkflowRule rule = new WorkflowRule();
        rule.setStep(workflowStepRepository.findById(dto.getStepId())
                .orElseThrow(() -> new RuntimeException("Step not found")));
        rule.setRoleId(dto.getRoleId());
        rule.setGroupId(dto.getGroupId());
        rule.setUserId(dto.getUserId());

        WorkflowRule saved = workflowRuleRepository.save(rule);
        return mapToDTO(saved);
    }

    @Override
    public List<WorkflowRuleDTO> getAll() {
        return workflowRuleRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WorkflowRuleDTO getById(Long id) {
        WorkflowRule rule = workflowRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        return mapToDTO(rule);
    }

    @Override
    public WorkflowRuleDTO update(Long id, WorkflowRuleDTO dto) {
        WorkflowRule rule = workflowRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        rule.setStep(workflowStepRepository.findById(dto.getStepId())
                .orElseThrow(() -> new RuntimeException("Step not found")));
        rule.setRoleId(dto.getRoleId());
        rule.setGroupId(dto.getGroupId());
        rule.setUserId(dto.getUserId());

        WorkflowRule updated = workflowRuleRepository.save(rule);
        return mapToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        workflowRuleRepository.deleteById(id);
    }

    private WorkflowRuleDTO mapToDTO(WorkflowRule rule) {
        WorkflowRuleDTO dto = new WorkflowRuleDTO();
        dto.setId(rule.getId());
        dto.setStepId(rule.getStep().getId());
        dto.setRoleId(rule.getRoleId());
        dto.setGroupId(rule.getGroupId());
        dto.setUserId(rule.getUserId());
        return dto;
    }
}
