package com.ridhitek.workflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridhitek.workflow.entity.WorkflowTaskType;

@Repository
public interface WorkflowTaskTypeRepository extends JpaRepository<WorkflowTaskType, Long> {
    Optional<WorkflowTaskType> findByWorkflowStepId(Long stepId);
}
