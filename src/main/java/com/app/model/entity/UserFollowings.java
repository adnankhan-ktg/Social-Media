package com.app.model.entity;

import jakarta.persistence.*;
import java.util.Date;
import com.app.model.enums.FollowStatus;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(
        name = "user_followings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"follower_user_id", "followed_user_id"}))
public class UserFollowings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "follower_user_id")
    private User followerUser;

    @ManyToOne
    @JoinColumn(name = "followed_user_id")
    private User followedUser;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt = new Date();

    @Enumerated(EnumType.STRING)
    private FollowStatus status;
}