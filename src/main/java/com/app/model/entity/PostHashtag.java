package com.app.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_hashtags")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relationship_id")
    private int relationshipId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}