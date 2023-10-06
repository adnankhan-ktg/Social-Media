package com.app.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "POST_HASHTAG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}