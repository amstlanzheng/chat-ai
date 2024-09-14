package com.amstlan.ai.common.Result;

public class ApiResponse<T> {
    private Integer success;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(Integer success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Integer success,  T data) {
        this.success = success;
        this.message = "操作成功";
        this.data = data;
    }
    public ApiResponse( T data) {
        this.success = 200;
        this.message = "操作成功";
        this.data = data;
    }

    public ApiResponse(Integer success, String message) {
        this.success = success;
        this.message = message;
    }
    public Integer isSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
