package com.app.model.entity;

import com.app.model.enums.InteractionType;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "interaction_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteractionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Enumerated(EnumType.STRING)
    @Column(name = "interaction_type")
    private InteractionType interactionType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = new Date();
}