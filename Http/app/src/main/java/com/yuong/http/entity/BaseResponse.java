package com.yuong.http.entity;

/**
 * Created by yuandong on 2018/7/13.
 */

public class BaseResponse {
    public String message;
    public int code;


    public BaseResponse() {
        super();
    }
    public BaseResponse(String message, int code) {
        super();
        this.message = message;
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
