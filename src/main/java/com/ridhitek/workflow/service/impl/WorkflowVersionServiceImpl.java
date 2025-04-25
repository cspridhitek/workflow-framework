package com.ridhitek.workflow.service.impl;

import com.ridhitek.workflow.dto.WorkflowVersionDTO;
import com.ridhitek.workflow.entity.Workflow;
import com.ridhitek.workflow.entity.WorkflowVersion;
import com.ridhitek.workflow.exception.WorkflowNotFoundException;
import com.ridhitek.workflow.repository.WorkflowRepository;
import com.ridhitek.workflow.repository.WorkflowVersionRepository;
import com.ridhitek.workflow.service.WorkflowVersionService;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkflowVersionServiceImpl implements WorkflowVersionService {

    private final WorkflowVersionRepository workflowVersionRepository;
    private final WorkflowRepository workflowRepository;

    public WorkflowVersionServiceImpl(WorkflowVersionRepository workflowVersionRepository, WorkflowRepository workflowRepository, WorkflowRepository workflowRepository1) {
        this.workflowVersionRepository = workflowVersionRepository;
        this.workflowRepository = workflowRepository1;
    }

    @Override
    public WorkflowVersionDTO getLatestActiveVersion(UUID workflowId) {
        Optional<WorkflowVersion> workflowVersion = workflowVersionRepository.findTopByWorkflowIdAndIsActiveTrueOrderByVersionDesc(workflowId);
        return workflowVersion.map(WorkflowVersionDTO::new).orElseThrow(() ->new RuntimeException("workflow not found"));
    }


    @Override
    public WorkflowVersionDTO saveVersion(WorkflowVersionDTO versionDTO) {
        Workflow workflow = workflowRepository.findById(UUID.fromString(versionDTO.getWorkflowId()))
                .orElseThrow(() -> new WorkflowNotFoundException("Workflow not found with id: " + versionDTO.getWorkflowId()));
        WorkflowVersion workflowVersion = new WorkflowVersion();
        workflowVersion.setWorkflow(workflow);
        workflowVersion.setVersion(versionDTO.getVersion());
        workflowVersion.setDescription(versionDTO.getDescription());
        workflowVersion.setIsActive(true);
        return new WorkflowVersionDTO(workflowVersion);

    }

    @Override
    public WorkflowVersionDTO updateWorkflowVersion(WorkflowVersionDTO versionDTO){
        Optional<Workflow> workflow = workflowRepository.findById(UUID.fromString(versionDTO.getWorkflowId()));
        Optional<WorkflowVersion> workflowVersion = workflowVersionRepository.findMaxVersionByWorkflowIdAndIsActiveTrue(UUID.fromString(versionDTO.getWorkflowId()));
        workflowVersion.ifPresentOrElse(
                workflowVersion1 -> {
                    workflowVersion1.setIsActive(false);
                    WorkflowVersion workflowVersion2 = new WorkflowVersion();
                    workflowVersion2.setVersion(versionDTO.getVersion());
                    workflowVersion2.setWorkflow(workflow.get());
                    workflowVersion2.setDescription(versionDTO.getDescription());
                    workflowVersion2.setIsActive(true);
                    workflowVersionRepository.save(workflowVersion2);

                },()->{
                    throw new RuntimeException("WOrkflow version is not found");
                }
        );
        return  new WorkflowVersionDTO();
    }

}
