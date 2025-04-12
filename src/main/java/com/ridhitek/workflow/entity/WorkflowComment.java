package com.ridhitek.workflow.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "workflow_comment")
public class WorkflowComment extends BaseAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private WorkflowTask task;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String comment;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}