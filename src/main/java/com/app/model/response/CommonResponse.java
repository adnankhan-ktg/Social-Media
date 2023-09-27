package com.app.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {

    private String msg;

    private int statusCode;

    private Object data;
}
