package com.ridhitek.workflow.service;

import java.util.List;

import com.ridhitek.workflow.dto.WorkflowTaskTypeDTO;

public interface WorkflowTaskTypeService {
    WorkflowTaskTypeDTO create(WorkflowTaskTypeDTO dto);

    WorkflowTaskTypeDTO getById(Long id);

    WorkflowTaskTypeDTO getByStepId(Long stepId);

    List<WorkflowTaskTypeDTO> getAll();

    WorkflowTaskTypeDTO update(Long id, WorkflowTaskTypeDTO dto);

    void delete(Long id);
}
