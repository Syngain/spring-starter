package com.hualongdata.domain;

/**
 * Created by yangbajing on 16-9-18.
 */
public class ApiError {
    private int errCode = 0;
    private String errMsg = "";

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
