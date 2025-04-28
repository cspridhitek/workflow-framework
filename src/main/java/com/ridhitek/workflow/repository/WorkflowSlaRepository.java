package com.ridhitek.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridhitek.workflow.entity.WorkflowSla;

@Repository
public interface WorkflowSlaRepository extends JpaRepository<WorkflowSla, Long> {
}
