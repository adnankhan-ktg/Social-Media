package com.app.model.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "User_Interactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Interaction_ID")
    private Long interactionId;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Post_ID")
    private Post post;

    @Column(name = "Interaction_Type")
    private String interactionType;

    @Column(name = "Timestamp")
    private Date timestamp;
}