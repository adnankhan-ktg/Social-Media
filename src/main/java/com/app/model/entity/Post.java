package com.app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "Content_Type")
    private String contentType;

    @Column(name = "Caption")
    private String caption;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = new Date();

    @Column(name = "post_url")
    private String postUrl;

    @ManyToMany(mappedBy = "fetchedPosts")
    private Set<User> fetchedByUsers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private PostCategoryMst category;
}