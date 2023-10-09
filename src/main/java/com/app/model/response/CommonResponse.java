package com.app.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResponse {

    private String msg;

    private int statusCode;

    private Object data;

    public CommonResponse(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }


}
