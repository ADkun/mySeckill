package com.adkun.mySeckill.common;

/**
 * 项目异常统一封装类
 * @author adkun
 */
public class BusinessException extends RuntimeException {

    // 封装在ErrorCode
    private int code;
    private String message;

    public BusinessException(int code) {
        super();
        this.code = code;
    }

    public BusinessException(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
