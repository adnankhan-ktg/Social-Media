package com.app.model.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "user_interactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Interaction_ID")
    private Long interactionId;

    @ManyToOne
    @JoinColumn(name = "user_iD")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "interaction_type")
    private String interactionType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp = new Date();

    public UserInteraction(User user, Post post, String interactionType) {
        this.user = user;
        this.post = post;
        this.interactionType = interactionType;
    }
}