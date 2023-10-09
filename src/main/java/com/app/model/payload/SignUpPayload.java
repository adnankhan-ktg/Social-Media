package com.app.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpPayload {

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNo;

    private String password;
}
