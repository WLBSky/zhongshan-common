package com.hebta.common.persistence;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseMessage<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 是否成功
     */
    private boolean success;

    protected String message;

    protected T data;

    protected int code;

    private Long timestamp;

    public static <T> ResponseMessage<T> error(String message) {
        return error(message, 500, null);
    }

    public static <T> ResponseMessage<T> error(T data) {
        return error(null, 500, data);
    }

    public static <T> ResponseMessage<T> error(String message, int code) {
        return error(message, code, null);
    }

    public static <T> ResponseMessage<T> error(String message, int code, T data) {
        ResponseMessage<T> msg = new ResponseMessage<>();
        msg.setMessage(message);
        msg.setCode(code);
        msg.setData(data);
        msg.setSuccess(false);
        return msg.putTimeStamp();
    }

    public static <T> ResponseMessage<T> created(T data) {
        return new ResponseMessage<T>()
                .data(data)
                .success(true)
                .putTimeStamp()
                .code(201);
    }

    public static <T> ResponseMessage<T> ok() {
        return ok(null);
    }

    private ResponseMessage<T> putTimeStamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public static <T> ResponseMessage<T> ok(T data) {
        return new ResponseMessage<T>()
                .data(data)
                .success(true)
                .putTimeStamp()
                .code(200);
    }

    public ResponseMessage<T> data(T data) {
        this.data = data;
        return this;
    }

    public ResponseMessage<T> success(boolean success) {
        this.success = success;
        return this;
    }

    public ResponseMessage() {

    }

    public ResponseMessage<T> code(int code) {
        this.code = code;
        return this;
    }

    /*********==   get set ==*********/
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return super.toString();
    }
}
