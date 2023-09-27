package com.app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

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

    @Column(name = "user_id")
    private int userId;

    @Column(name = "Content_Type")
    private String contentType;

    @Column(name = "Caption")
    private String caption;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = new Date();

    @Column(name = "post_url")
    private String postUrl;
}