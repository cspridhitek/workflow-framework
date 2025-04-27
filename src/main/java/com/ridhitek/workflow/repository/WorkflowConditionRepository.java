package com.ridhitek.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ridhitek.workflow.entity.WorkflowCondition;

public interface WorkflowConditionRepository extends JpaRepository<WorkflowCondition, Long> {
    List<WorkflowCondition> findByStepFromId(Long stepFromId);

}
