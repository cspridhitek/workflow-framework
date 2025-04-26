package com.ridhitek.workflow.service.impl;

import com.ridhitek.workflow.dto.WorkflowVersionDTO;
import com.ridhitek.workflow.entity.Workflow;
import com.ridhitek.workflow.entity.WorkflowVersion;
import com.ridhitek.workflow.exception.WorkflowNotFoundException;
import com.ridhitek.workflow.repository.WorkflowRepository;
import com.ridhitek.workflow.repository.WorkflowVersionRepository;
import com.ridhitek.workflow.service.WorkflowVersionService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class WorkflowVersionServiceImpl implements WorkflowVersionService {

    private final WorkflowVersionRepository workflowVersionRepository;
    private final WorkflowRepository workflowRepository;

    public WorkflowVersionServiceImpl(WorkflowVersionRepository workflowVersionRepository,
            WorkflowRepository workflowRepository) {
        this.workflowVersionRepository = workflowVersionRepository;
        this.workflowRepository = workflowRepository;
    }

    @Override
    public WorkflowVersionDTO getLatestActiveVersion(UUID workflowId) {
        Optional<WorkflowVersion> workflowVersion = workflowVersionRepository
                .findTopByWorkflowIdAndIsActiveTrueOrderByVersionDesc(workflowId);
        return workflowVersion.map(WorkflowVersionDTO::new)
                .orElseThrow(() -> new WorkflowNotFoundException("Workflow not found with id: " + workflowId));
    }

    @Override
    public WorkflowVersionDTO saveVersion(WorkflowVersionDTO versionDTO) {
        Workflow workflow = workflowRepository.findById(UUID.fromString(versionDTO.getWorkflowId()))
                .orElseThrow(() -> new WorkflowNotFoundException(
                        "Workflow not found with id: " + versionDTO.getWorkflowId()));
        WorkflowVersion workflowVersion = new WorkflowVersion();
        workflowVersion.setWorkflow(workflow);
        workflowVersion.setVersion(versionDTO.getVersion());
        workflowVersion.setDescription(versionDTO.getDescription());
        workflowVersion.setIsActive(true);
        workflowVersionRepository.save(workflowVersion);
        return new WorkflowVersionDTO(workflowVersion);
    }

    @Override
    public WorkflowVersionDTO updateWorkflowVersion(WorkflowVersionDTO versionDTO) {
        Workflow workflow = workflowRepository.findById(UUID.fromString(versionDTO.getWorkflowId()))
                .orElseThrow(() -> new WorkflowNotFoundException(
                        "Workflow not found with id: " + versionDTO.getWorkflowId()));

        Optional<WorkflowVersion> currentActiveVersion = workflowVersionRepository
                .findTopByWorkflowIdAndIsActiveTrueOrderByVersionDesc(UUID.fromString(versionDTO.getWorkflowId()));
        currentActiveVersion.ifPresent(activeVersion -> {
            activeVersion.setIsActive(false);
            workflowVersionRepository.save(activeVersion);
        });

        WorkflowVersion newVersion = new WorkflowVersion();
        newVersion.setWorkflow(workflow);
        newVersion.setVersion(versionDTO.getVersion());
        newVersion.setDescription(versionDTO.getDescription());
        newVersion.setIsActive(true);
        workflowVersionRepository.save(newVersion);

        return new WorkflowVersionDTO(newVersion);
    }
}
