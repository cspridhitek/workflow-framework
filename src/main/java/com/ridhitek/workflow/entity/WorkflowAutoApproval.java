package com.ridhitek.workflow.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workflow_auto_approval")
public class WorkflowAutoApproval extends BaseAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "step_id", nullable = false)
    private WorkflowStep step;

    private String conditionField;
    private String operator;
    private String conditionValue;
    private Boolean autoApprove;
}