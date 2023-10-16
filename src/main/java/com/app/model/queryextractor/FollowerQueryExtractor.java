package com.app.model.queryextractor;

import lombok.*;


@NoArgsConstructor
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
public class FollowerQueryExtractor {

    private int id;

    private String firstName;

    private String lastName;

    private String email;
}

