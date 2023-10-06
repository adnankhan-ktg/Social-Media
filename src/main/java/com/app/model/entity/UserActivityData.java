package com.app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "USER_ACTIVITY_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private CategoryMst category;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp = new Date();
    
    @Column(name = "activity_type")
    private String activityType;
}
