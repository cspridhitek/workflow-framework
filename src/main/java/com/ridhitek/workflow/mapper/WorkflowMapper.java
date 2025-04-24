package com.ridhitek.workflow.mapper;

import com.ridhitek.workflow.dto.*;
import com.ridhitek.workflow.entity.Workflow;

public class WorkflowMapper {

    public static WorkflowRequestDTO toDto(Workflow workflow) {
        return WorkflowRequestDTO.builder()
                .name(workflow.getName())
                .description(workflow.getDescription())
                .build();
    }

    public static  WorkflowResponseDTO toResponseDTO(Workflow entity) {
        WorkflowResponseDTO dto = new WorkflowResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static Workflow toEntity(WorkflowRequestDTO dto) {
        return Workflow.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static Workflow toResponseEntity(WorkflowResponseDTO dto) {
        return Workflow.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static void updateEntity(Workflow workflow, WorkflowRequestDTO dto) {
        workflow.setName(dto.getName());
        workflow.setDescription(dto.getDescription());
    }
}
