package com.ridhitek.workflow.service.impl;

import com.ridhitek.workflow.dto.WorkflowSlaDTO;
import com.ridhitek.workflow.entity.WorkflowSla;
import com.ridhitek.workflow.entity.WorkflowStep;
import com.ridhitek.workflow.repository.WorkflowSlaRepository;
import com.ridhitek.workflow.repository.WorkflowStepRepository;
import com.ridhitek.workflow.service.WorkflowSlaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkflowSlaServiceImpl implements WorkflowSlaService {

    private final WorkflowSlaRepository workflowSlaRepository;
    private final WorkflowStepRepository workflowStepRepository;

    @Override
    public WorkflowSlaDTO createSla(WorkflowSlaDTO dto) {
        WorkflowStep step = workflowStepRepository.findById(dto.getWorkflowStepId())
                .orElseThrow(() -> new RuntimeException("Workflow Step not found"));

        WorkflowSla sla = new WorkflowSla();
        sla.setStep(step);
        sla.setExpectedCompletionTimeMins(dto.getExpectedCompletionTimeMins());

        sla = workflowSlaRepository.save(sla);

        dto.setId(sla.getId());
        return dto;
    }

    @Override
    public WorkflowSlaDTO updateSla(Long id, WorkflowSlaDTO dto) {
        WorkflowSla sla = workflowSlaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow SLA not found"));

        WorkflowStep step = workflowStepRepository.findById(dto.getWorkflowStepId())
                .orElseThrow(() -> new RuntimeException("Workflow Step not found"));

        sla.setStep(step);
        sla.setExpectedCompletionTimeMins(dto.getExpectedCompletionTimeMins());

        sla = workflowSlaRepository.save(sla);

        dto.setId(sla.getId());
        return dto;
    }

    @Override
    public WorkflowSlaDTO getSlaById(Long id) {
        WorkflowSla sla = workflowSlaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow SLA not found"));

        WorkflowSlaDTO dto = new WorkflowSlaDTO();
        dto.setId(sla.getId());
        dto.setWorkflowStepId(sla.getStep().getId());
        dto.setExpectedCompletionTimeMins(sla.getExpectedCompletionTimeMins());
        return dto;
    }

    @Override
    public List<WorkflowSlaDTO> getAllSlas() {
        return workflowSlaRepository.findAll().stream().map(sla -> {
            WorkflowSlaDTO dto = new WorkflowSlaDTO();
            dto.setId(sla.getId());
            dto.setWorkflowStepId(sla.getStep().getId());
            dto.setExpectedCompletionTimeMins(sla.getExpectedCompletionTimeMins());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteSla(Long id) {
        workflowSlaRepository.deleteById(id);
    }
}
