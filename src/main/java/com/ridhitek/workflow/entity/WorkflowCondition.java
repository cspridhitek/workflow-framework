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
@Table(name = "workflow_condition")
public class WorkflowCondition extends BaseAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workflow_step_id_from", nullable = false)
    private WorkflowStep stepFrom; // Source step

    @ManyToOne
    @JoinColumn(name = "workflow_step_id_to", nullable = false)
    private WorkflowStep stepTo; // Target step

    @Column(name = "condition_type")
    private String conditionType; // E.g., Days > 5, Approval

    @Column(name = "condition_value")
    private String conditionValue; // E.g., Yes, Approved

    // Getters and Setters
}