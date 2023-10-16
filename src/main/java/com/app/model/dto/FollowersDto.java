package com.app.model.dto;

import lombok.*;

@NoArgsConstructor
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
public class FollowersDto {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

}
