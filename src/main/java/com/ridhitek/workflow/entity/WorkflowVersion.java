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
@Table(name = "workflow_version")
public class WorkflowVersion extends BaseAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "workflow_id", nullable = false,columnDefinition = "BINARY(16)")
    private Workflow workflow;

    private String version;
    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

}