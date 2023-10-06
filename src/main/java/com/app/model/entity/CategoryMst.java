package com.app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CATEGORY_MST")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
