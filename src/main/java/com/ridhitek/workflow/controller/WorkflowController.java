package com.ridhitek.workflow.controller;

import com.ridhitek.workflow.dto.WorkflowRequestDTO;
import com.ridhitek.workflow.dto.WorkflowResponseDTO;
import com.ridhitek.workflow.service.WorkflowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @PostMapping
    public ResponseEntity<WorkflowResponseDTO> createWorkflow(@RequestBody @Valid WorkflowRequestDTO workflowRequestDTO) {
        return new ResponseEntity<>(workflowService.createWorkflow(workflowRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkflowResponseDTO>> getAllWorkflows() {
        return ResponseEntity.ok(workflowService.getAllWorkflows());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowResponseDTO> getWorkflowById(@PathVariable UUID id) {
        return ResponseEntity.ok(workflowService.getWorkflowById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkflowResponseDTO> updateWorkflow(@PathVariable UUID id, @RequestBody  @Valid WorkflowRequestDTO workflowRequestDTO) {
        return ResponseEntity.ok(workflowService.updateWorkflow(id, workflowRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkflow(@PathVariable UUID id) {
        workflowService.deleteWorkflow(id);
        return ResponseEntity.noContent().build();
    }
}
