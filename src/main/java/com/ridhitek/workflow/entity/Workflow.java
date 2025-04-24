package com.ridhitek.workflow.entity;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "workflow")
public class Workflow extends BaseAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;

    private String name;
    private String description;

    // Getters and Setters

}