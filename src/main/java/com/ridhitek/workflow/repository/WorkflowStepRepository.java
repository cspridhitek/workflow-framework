package com.ridhitek.workflow.repository;

import com.ridhitek.workflow.entity.WorkflowStep;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowStepRepository extends JpaRepository<WorkflowStep, Long> {
    List<WorkflowStep> findByWorkflow_Id(UUID workflowId);

    void deleteByWorkflow_Id(UUID workflowId);

}
