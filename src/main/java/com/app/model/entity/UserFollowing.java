package com.app.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "User_Following")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFollowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Relationship_ID")
    private Long relationshipId;

    @ManyToOne
    @JoinColumn(name = "Follower_User_ID")
    private User followerUser;

    @ManyToOne
    @JoinColumn(name = "Following_User_ID")
    private User followingUser;

    @Column(name = "Follow_Date")
    private Date followDate;

}