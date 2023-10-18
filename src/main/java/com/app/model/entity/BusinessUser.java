package com.app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "business_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "password")
    private String password;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "business_category")
    private String businessCategory;

    @Column(name = "website")
    private String website;

    @Temporal(TemporalType.DATE)
    @Column(name = "joining_date")
    private Date joiningDate = new Date();

    @Column(name = "bio", length = 255)  // Adjust the length as needed
    private String bio;
}
