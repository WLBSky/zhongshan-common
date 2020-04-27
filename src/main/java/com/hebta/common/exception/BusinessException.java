package com.hebta.common.exception;

import org.springframework.http.HttpStatus;

import com.hebta.common.enums.CodeEnum;


/**
 * @author Han.Sun
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -1L;
    private HttpStatus httpStatus;
    private int code;

    public BusinessException(CodeEnum codeEnum) {
        this(codeEnum.getMessage(),codeEnum.getCode());
    }
    
    public BusinessException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    public BusinessException(String message, int code) {
        this(message, HttpStatus.BAD_REQUEST,code);
    }

    public BusinessException(int code,String message) {
        this(message, HttpStatus.BAD_REQUEST,code);
    }


    public BusinessException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public BusinessException(String message, HttpStatus httpStatus, int code) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public BusinessException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
