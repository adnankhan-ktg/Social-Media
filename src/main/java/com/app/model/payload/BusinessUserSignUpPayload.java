package com.app.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserSignUpPayload {

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNo;

    private String password;

    private String bio;

    private String businessName;

    private String businessCategory;

    private String website;
}
