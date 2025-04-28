package com.ridhitek.workflow.service;

import java.util.List;

import com.ridhitek.workflow.dto.WorkflowSlaDTO;

public interface WorkflowSlaService {

    WorkflowSlaDTO createSla(WorkflowSlaDTO dto);

    WorkflowSlaDTO updateSla(Long id, WorkflowSlaDTO dto);

    WorkflowSlaDTO getSlaById(Long id);

    List<WorkflowSlaDTO> getAllSlas();

    void deleteSla(Long id);

}
