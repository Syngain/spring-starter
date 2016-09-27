package com.hualongdata.domain;

/**
 * API响应消息
 * Created by yangbajing on 16-9-12.
 */
public class ApiResponse {
    public static <T> T success(T data) {
        return data;
    }

    public static ApiError error(int code, String message) {
        ApiError resp = new ApiError();
        resp.setErrCode(code);
        resp.setErrMsg(message);
        return resp;
    }

    public static ApiError error(String message) {
        return error(500, message);
    }

}
