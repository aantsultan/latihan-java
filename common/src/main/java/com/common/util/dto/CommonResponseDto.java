package com.common.util.dto;

public class CommonResponseDto {

    private Object data;
    private Integer status;
    private String message;

    public CommonResponseDto() {
    }

    public CommonResponseDto(Object data, Integer status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
