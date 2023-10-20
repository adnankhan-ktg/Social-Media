package com.app.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(
        name = "tag_post_association",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "post_id"})
)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagPostAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
}
