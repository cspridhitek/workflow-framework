package com.ridhitek.workflow.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository; // Import JpaRepository
import com.ridhitek.workflow.entity.Workflow; // Import the Workflow class

import java.util.UUID;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, UUID> {

}
