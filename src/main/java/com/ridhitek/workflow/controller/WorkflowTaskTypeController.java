package com.ridhitek.workflow.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ridhitek.workflow.dto.WorkflowTaskTypeDTO;
import com.ridhitek.workflow.service.WorkflowTaskTypeService;

@RestController
@RequestMapping("/api/workflow-task-types")
public class WorkflowTaskTypeController {

    private final WorkflowTaskTypeService workflowTaskTypeService;

    public WorkflowTaskTypeController(WorkflowTaskTypeService workflowTaskTypeService) {
        this.workflowTaskTypeService = workflowTaskTypeService;
    }

    @PostMapping
    public ResponseEntity<WorkflowTaskTypeDTO> create(@RequestBody WorkflowTaskTypeDTO dto) {
        WorkflowTaskTypeDTO created = workflowTaskTypeService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkflowTaskTypeDTO>> getAll() {
        return ResponseEntity.ok(workflowTaskTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowTaskTypeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(workflowTaskTypeService.getById(id));
    }

    @GetMapping("/step/{stepId}")
    public ResponseEntity<WorkflowTaskTypeDTO> getByStepId(@PathVariable Long stepId) {
        return ResponseEntity.ok(workflowTaskTypeService.getByStepId(stepId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkflowTaskTypeDTO> update(@PathVariable Long id, @RequestBody WorkflowTaskTypeDTO dto) {
        return ResponseEntity.ok(workflowTaskTypeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workflowTaskTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
