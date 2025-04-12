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
@Table(name = "notification_log")
public class NotificationLog extends BaseAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private NotificationTemplate template;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String channel;
    private String status;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;
}