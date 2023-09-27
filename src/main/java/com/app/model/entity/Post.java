package com.app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Post_ID")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private User user;

    @Column(name = "Content_Type")
    private String contentType;

    @Column(name = "Caption")
    private String caption;

    @Column(name = "Timestamp")
    private Date timestamp;

    // Getters and
}