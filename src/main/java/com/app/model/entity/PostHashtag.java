package com.app.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Post_Hashtags")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Relationship_ID")
    private Long relationshipId;

    @ManyToOne
    @JoinColumn(name = "Post_ID")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "Hashtag_ID")
    private Hashtag hashtag;
}