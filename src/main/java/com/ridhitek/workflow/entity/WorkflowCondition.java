package com.ridhitek.workflow.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workflow_condition")
public class WorkflowCondition extends BaseAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "step_id", nullable = false)
    private WorkflowStep step;

    @Column(name = "field_name")
    private String fieldName;

    private String operator;

    @Column(name = "expected_value")
    private String expectedValue;

    @Column(name = "target_step_id")
    private Long targetStepId;

}