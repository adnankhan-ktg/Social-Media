package com.app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATEGORY_SCORE")
public class CategoryScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_category_score_id")
    private UserCategoryScore userCategoryScore;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryMst category;

    @Column(name = "score_value")
    private int scoreValue;
}
