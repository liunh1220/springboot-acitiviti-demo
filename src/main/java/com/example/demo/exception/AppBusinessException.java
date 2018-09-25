package com.example.demo.exception;

import com.example.demo.base.ErrorCode;
import com.example.demo.constant.CommonErrorCode;

/**
 * Created by liunanhua on 2018/5/9.
 */
public class AppBusinessException extends BaseException {

    public static final ErrorCode DEFAULT_CODE = CommonErrorCode.INTERNAL_ERROR;

    private String code = DEFAULT_CODE.getCode();

    //类似Http状态码
    private int httpStatus = DEFAULT_CODE.getStatus();

    private AppBusinessException(String code, int httpStatus, String message) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public AppBusinessException(String message) {
        super(message);
    }

    /**
     * @param errorCode 状态码, 这个字段会在错误信息里返回给客户端.
     * @param message
     */
    public AppBusinessException(ErrorCode errorCode, String message) {
        this(errorCode.getCode(), errorCode.getStatus(), message);
    }

    public AppBusinessException(ErrorCode errorCode) {
        this(errorCode, errorCode.getMessage());
    }

    public String getCode() {
        return code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}