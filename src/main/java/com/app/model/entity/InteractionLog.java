package com.app.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "INTERACTION_LOG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteractionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "interaction_type")
    private String interactionType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = new Date();
}