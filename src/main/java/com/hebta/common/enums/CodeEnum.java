package com.hebta.common.enums;

/**
 * @author Han.Sun
 * @since 2017年07月28日
 */
public enum CodeEnum {
    PARAM_ERROR(50000, "参数不合法"),
    ILLEGAL_OPERATION(40014, "非法操作");

    private int code;
    private String message;

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
