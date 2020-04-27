package com.hebta.common.persistence;

/**
 * api response body for v2
 *
 * @author sanlion.do
 * @version v2.0 @2015-3-31
 */
public class ApiResponse {

    private static final String RESULT_OK = "OK";
    private static final String RESULT_FAIL = "FAIL";
    private static final ApiBody OkBody = new ApiBody();

    private String result = RESULT_OK;
    private int errorCode;
    private String errorDescription;
    private ApiBody body;

    public ApiResponse() {
    }

    public static ApiResponse OK() {
        ApiResponse ok = new ApiResponse();
        ok.result = RESULT_OK;
        ok.body = OkBody;
        return ok;
    }

    public static ApiResponse OK(ApiBody okBody) {
        ApiResponse ok = new ApiResponse();
        ok.result = RESULT_OK;
        ok.body = okBody;
        return ok;
    }

    public static ApiResponse FAIL(int errorCode, String errorDescription) {
        ApiResponse fail = new ApiResponse();
        fail.result = RESULT_FAIL;
        fail.errorCode = errorCode;
        fail.errorDescription = errorDescription;
        return fail;
    }

    public ApiResponse body(ApiBody body) {
        this.body = body;
        return this;
    }

    public ApiResponse description(String description) {
        this.errorDescription = description;
        return this;
    }

    public ApiResponse code(int code) {
        this.errorCode = code;
        return this;
    }

    @Override
    public String toString() {
        return "ApiResponse{" + "result=" + result + ", errorCode=" + errorCode + ", errorDescription=" + errorDescription + ", body=" + body + '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public ApiBody getBody() {
        return body;
    }

    public void setBody(ApiBody body) {
        this.body = body;
    }

    public static class Promotion extends ApiBody {

        private String name;

        public Promotion(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
