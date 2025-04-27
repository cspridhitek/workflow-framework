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

import com.ridhitek.workflow.dto.WorkflowConditionDTO;
import com.ridhitek.workflow.service.WorkflowConditionService;

@RestController
@RequestMapping("/api/workflow-conditions")
public class WorkflowConditionController {

    private final WorkflowConditionService workflowConditionService;

    public WorkflowConditionController(WorkflowConditionService workflowConditionService) {
        this.workflowConditionService = workflowConditionService;
    }

    @PostMapping
    public ResponseEntity<WorkflowConditionDTO> create(@RequestBody WorkflowConditionDTO dto) {
        WorkflowConditionDTO created = workflowConditionService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkflowConditionDTO>> getAll() {
        List<WorkflowConditionDTO> conditions = workflowConditionService.getAll();
        return ResponseEntity.ok(conditions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowConditionDTO> getById(@PathVariable Long id) {
        WorkflowConditionDTO condition = workflowConditionService.getById(id);
        return ResponseEntity.ok(condition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkflowConditionDTO> update(@PathVariable Long id, @RequestBody WorkflowConditionDTO dto) {
        WorkflowConditionDTO updated = workflowConditionService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workflowConditionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/step-from/{stepFromId}")
    public ResponseEntity<List<WorkflowConditionDTO>> getConditionsByStepFrom(@PathVariable Long stepFromId) {
        List<WorkflowConditionDTO> conditions = workflowConditionService.findByStepFrom(stepFromId);
        return ResponseEntity.ok(conditions);
    }
}
