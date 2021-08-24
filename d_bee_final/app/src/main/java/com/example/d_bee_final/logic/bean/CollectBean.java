package com.example.d_bee_final.logic.bean;

public class CollectBean {

    private String data;
    private int errorCode;
    private String errorMsg;
    public void setData(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public String getErrorMsg() {
        return errorMsg;
    }

}