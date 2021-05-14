package com.cac.mycontacts.dto;

public class RestResourceResponse {
    String errorCode;
    String errorMessage;
    Object data;

    public RestResourceResponse() {
    }

    public RestResourceResponse(String errorCode, String errorMessage, Object data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
