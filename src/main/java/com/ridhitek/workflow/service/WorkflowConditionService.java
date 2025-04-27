package com.ridhitek.workflow.service;

import java.util.List;

import com.ridhitek.workflow.dto.WorkflowConditionDTO;

public interface WorkflowConditionService {

    WorkflowConditionDTO create(WorkflowConditionDTO dto);

    List<WorkflowConditionDTO> getAll();

    WorkflowConditionDTO getById(Long id);

    WorkflowConditionDTO update(Long id, WorkflowConditionDTO dto);

    void delete(Long id);

    List<WorkflowConditionDTO> findByStepFrom(Long stepFromId);

}
