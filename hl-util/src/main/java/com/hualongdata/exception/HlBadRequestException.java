package com.hualongdata.exception;

/**
 * 400 Bad Request
 * Created by yangbajing on 16-9-9.
 */
public class HlBadRequestException extends HlBaseException {
    public static final int ERR_CODE = 400001;

    public HlBadRequestException(String errMsg) {
        this(ERR_CODE, errMsg, null);
    }

    public HlBadRequestException(int errCode, String errMsg, Object data) {
        super(errCode, errMsg, data);
    }
}
