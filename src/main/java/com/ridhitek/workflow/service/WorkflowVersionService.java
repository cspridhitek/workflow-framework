package com.ridhitek.workflow.service;

import com.ridhitek.workflow.dto.WorkflowVersionDTO;

import java.util.List;
import java.util.UUID;


public interface WorkflowVersionService {

    WorkflowVersionDTO getLatestActiveVersion(UUID workflowId);
    WorkflowVersionDTO saveVersion(WorkflowVersionDTO version); // Used internally when defining workflow
    WorkflowVersionDTO updateWorkflowVersion(WorkflowVersionDTO versionDTO);
}
