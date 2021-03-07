package com.adkun.mySeckill.common;

/**
 * 返回给前端的json
 * @author adkun
 */
public class ResponseModel {

    /**
     * 状态常量
     */
    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_FAILURE = 1;

    /**
     * 业务状态
     */
    private int status;

    /**
     * 业务数据
     */
    private Object data;

    /**
     * 构造器
     * 无status参数者默认SUCCESS
     */
    public ResponseModel() {
        this.status = STATUS_SUCCESS;
    }

    public ResponseModel(Object data) {
        this.status = STATUS_SUCCESS;
        this.data = data;
    }

    public ResponseModel(int status, Object data) {
        this.status = status;
        this.data = data;
    }


    public int getStatus() {
        return status;
    }

    public ResponseModel setStatus(int status) {
        this.status = status;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseModel setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
