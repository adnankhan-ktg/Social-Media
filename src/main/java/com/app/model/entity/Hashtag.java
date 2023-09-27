package com.app.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Hashtags")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Hashtag_ID")
    private Long hashtagId;

    @Column(name = "Hashtag_Name")
    private String hashtagName;
}

