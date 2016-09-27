package com.hualongdata.exception;

/**
 * Created by yangbajing on 16-9-9.
 */
public abstract class HlBaseException extends RuntimeException {
    private int errCode;
    private String errMsg;
    private Object data;

    public HlBaseException(int errCode, String errMsg) {
        this(errCode, errMsg, null);
    }

    public HlBaseException(int errCode, String errMsg, Object data) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

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
