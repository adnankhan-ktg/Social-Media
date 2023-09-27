package com.app.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User_Interests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Interest_ID")
    private Long interestId;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private User user;

    @Column(name = "Interest_Name")
    private String interestName;
}