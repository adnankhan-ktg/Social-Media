package com.app.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_interests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Interest_ID")
    private Long interestId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "interest_name")
    private String interestName;
}