package com.app.model.entity;

import com.app.model.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "business_user_id")
    private BusinessUser businessUser;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "caption")
    private String caption;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = new Date();

    @Column(name = "post_url")
    private String postUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryMst category;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @ManyToMany
    @JoinTable(name = "post_hashtags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    private List<HashtagMst> hashtags;
}