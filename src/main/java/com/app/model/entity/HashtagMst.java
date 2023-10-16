package com.app.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "hashtag_mst")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HashtagMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt = new Date();

    @ManyToMany
    @JoinTable(
            name = "hashtag_category",
            joinColumns = @JoinColumn(name = "hashtag_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryMst> categories;
}

