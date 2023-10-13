package com.app.helper;


import com.app.model.response.CommonResponse;

public class CommonResHelper {

    public static CommonResponse internalServerError() {
        return new CommonResponse("Internal Server Error", 500);
    }
}
