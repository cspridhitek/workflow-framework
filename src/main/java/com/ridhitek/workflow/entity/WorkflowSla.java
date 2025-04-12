package com.ridhitek.workflow.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workflow_sla")
public class WorkflowSla extends BaseAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "step_id", nullable = false)
    private WorkflowStep step;

    @Column(name = "expected_completion_time_mins")
    private Integer expectedCompletionTimeMins;

    @Column(name = "notify_on_violation")
    private Boolean notifyOnViolation;

}