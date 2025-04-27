package com.ridhitek.workflow.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ridhitek.workflow.dto.WorkflowRuleDTO;
import com.ridhitek.workflow.service.WorkflowRuleService;

@RestController
@RequestMapping("/api/workflow-rules")
public class WorkflowRuleController {

    private final WorkflowRuleService workflowRuleService;

    public WorkflowRuleController(WorkflowRuleService workflowRuleService) {
        this.workflowRuleService = workflowRuleService;
    }

    @PostMapping
    public ResponseEntity<WorkflowRuleDTO> create(@RequestBody WorkflowRuleDTO workflowRuleDTO) {
        return ResponseEntity.ok(workflowRuleService.create(workflowRuleDTO));
    }

    @GetMapping
    public ResponseEntity<List<WorkflowRuleDTO>> getAll() {
        return ResponseEntity.ok(workflowRuleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowRuleDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(workflowRuleService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkflowRuleDTO> update(@PathVariable Long id, @RequestBody WorkflowRuleDTO workflowRuleDTO) {
        return ResponseEntity.ok(workflowRuleService.update(id, workflowRuleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workflowRuleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
