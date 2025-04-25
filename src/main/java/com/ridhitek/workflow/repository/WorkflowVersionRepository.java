package com.ridhitek.workflow.repository;

import com.ridhitek.workflow.entity.WorkflowVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkflowVersionRepository extends JpaRepository<WorkflowVersion, Integer> {

    Optional<WorkflowVersion> findTopByWorkflowIdAndIsActiveTrueOrderByVersionDesc(UUID workflowId);

    @Query("Select max(wv.version) from WorkflowVersion as wv where wv.workflowId=?1 and isActive=true")
    Optional<WorkflowVersion> findMaxVersionByWorkflowIdAndIsActiveTrue(UUID workflowId);
}
