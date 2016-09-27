package com.hualongdata.exception;

/**
 * Created by yangbajing on 16-9-9.
 */
public class HlUnauthorizedException extends HlBaseException {
    public final static int ERR_CODE = 401001;

    public HlUnauthorizedException() {
        this(ERR_CODE, "", null);
    }

    public HlUnauthorizedException(String message) {
        this(ERR_CODE, message, null);
    }

    public HlUnauthorizedException(int errCode, String errMsg, Object data) {
        super(errCode, errMsg, data);
    }
}
