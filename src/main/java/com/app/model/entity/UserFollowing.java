package com.app.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "user_following")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFollowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relationship_iD")
    private Long relationshipId;

    @ManyToOne
    @JoinColumn(name = "follower_user_iD")
    private User followerUser;

    @ManyToOne
    @JoinColumn(name = "following_user_iD")
    private User followingUser;

    @Column(name = "follow_date")
    private Date followDate;

}