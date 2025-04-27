package com.ridhitek.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ridhitek.workflow.entity.WorkflowRule;

public interface WorkflowRuleRepository extends JpaRepository<WorkflowRule, Long> {

}
