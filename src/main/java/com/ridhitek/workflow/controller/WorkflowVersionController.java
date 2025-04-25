package com.ridhitek.workflow.controller;


import com.ridhitek.workflow.dto.WorkflowVersionDTO;
import com.ridhitek.workflow.service.WorkflowVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workflow-versions")
public class WorkflowVersionController {

    private final WorkflowVersionService workflowVersionService;

    public WorkflowVersionController(WorkflowVersionService workflowVersionService) {
        this.workflowVersionService = workflowVersionService;
    }

    @GetMapping("/{workflowId}")
    public ResponseEntity<?> getLatestActiveVersion(@PathVariable String workflowId){
        try{
            return ResponseEntity.ok(workflowVersionService.getLatestActiveVersion(UUID.fromString(workflowId)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping()
    public ResponseEntity<?> saveWorkflowVersion(@RequestBody WorkflowVersionDTO workflowVersionDTO){
        try {
            return ResponseEntity.ok(workflowVersionService.saveVersion(workflowVersionDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping()
    public ResponseEntity<?> updateWorkflowVersion(@RequestBody WorkflowVersionDTO workflowVersionDTO){
        try {
            return ResponseEntity.ok(workflowVersionService.updateWorkflowVersion(workflowVersionDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
