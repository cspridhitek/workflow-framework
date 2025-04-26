package com.ridhitek.workflow.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ridhitek.workflow.dto.WorkflowStepDTO;
import com.ridhitek.workflow.service.WorkflowStepService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/workflow-steps")
public class WorkflowStepController {

    private final WorkflowStepService workflowStepService;

    public WorkflowStepController(WorkflowStepService workflowStepService) {
        this.workflowStepService = workflowStepService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkflowStepById(@PathVariable Long id) {
        return ResponseEntity.ok(workflowStepService.getWorkflowStepById(id));
    }

    @PostMapping
    public ResponseEntity<?> createWorkflowStep(@RequestBody @Valid WorkflowStepDTO workflowStepDTO) {
        workflowStepService.createWorkflowStep(workflowStepDTO);
        return ResponseEntity.ok("Workflow step created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkflowStep(@PathVariable Long id,
            @RequestBody @Valid WorkflowStepDTO workflowStepDTO) {
        workflowStepService.updateWorkflowStep(id, workflowStepDTO);
        return ResponseEntity.ok("Workflow step updated successfully");
    }

    @GetMapping("/{workflowId}/steps")
    public ResponseEntity<?> getWorkflowStepsByWorkflowId(@PathVariable UUID workflowId) {
        return ResponseEntity.ok(workflowStepService.getWorkflowStepsByWorkflowId(workflowId));
    }

    @DeleteMapping("/{workflowId}/steps")
    public ResponseEntity<?> deleteWorkflowStep(@PathVariable UUID workflowId) {
        workflowStepService.deleteWorkflowStep(workflowId);
        return ResponseEntity.ok("Workflow step deleted successfully");
    }

    @DeleteMapping("/{workflowId}/steps/{stepId}")
    public ResponseEntity<?> deleteWorkflowStepById(@PathVariable UUID workflowId, @PathVariable Integer stepId) {
        workflowStepService.deleteWorkflowStepById(workflowId, stepId);
        return ResponseEntity.ok("Workflow step deleted successfully");
    }

}
