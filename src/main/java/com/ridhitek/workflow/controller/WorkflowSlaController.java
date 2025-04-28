package com.ridhitek.workflow.controller;

import com.ridhitek.workflow.dto.WorkflowSlaDTO;
import com.ridhitek.workflow.service.WorkflowSlaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workflow-sla")
public class WorkflowSlaController {

    private final WorkflowSlaService workflowSlaService;

    public WorkflowSlaController(WorkflowSlaService workflowSlaService) {
        this.workflowSlaService = workflowSlaService;
    }

    @PostMapping
    public ResponseEntity<WorkflowSlaDTO> createSla(@RequestBody WorkflowSlaDTO dto) {
        return ResponseEntity.ok(workflowSlaService.createSla(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkflowSlaDTO> updateSla(@PathVariable Long id, @RequestBody WorkflowSlaDTO dto) {
        return ResponseEntity.ok(workflowSlaService.updateSla(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowSlaDTO> getSlaById(@PathVariable Long id) {
        return ResponseEntity.ok(workflowSlaService.getSlaById(id));
    }

    @GetMapping
    public ResponseEntity<List<WorkflowSlaDTO>> getAllSlas() {
        return ResponseEntity.ok(workflowSlaService.getAllSlas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSla(@PathVariable Long id) {
        workflowSlaService.deleteSla(id);
        return ResponseEntity.noContent().build();
    }
}
