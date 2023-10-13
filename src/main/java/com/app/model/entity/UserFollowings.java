package com.app.model.entity;

import com.app.model.enums.FollowStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "user_following")
@Data
@NoArgsConstructor
@AllArgsConstructor
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