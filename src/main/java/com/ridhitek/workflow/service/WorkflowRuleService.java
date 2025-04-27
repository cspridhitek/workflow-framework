package com.ridhitek.workflow.service;

import java.util.List;

import com.ridhitek.workflow.dto.WorkflowRuleDTO;

public interface WorkflowRuleService {
    WorkflowRuleDTO create(WorkflowRuleDTO dto);

    List<WorkflowRuleDTO> getAll();

    WorkflowRuleDTO getById(Long id);

    WorkflowRuleDTO update(Long id, WorkflowRuleDTO dto);

    void delete(Long id);
}
