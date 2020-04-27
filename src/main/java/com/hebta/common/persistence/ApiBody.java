package com.hebta.common.persistence;

import com.hebta.common.enums.CodeEnum;

/**
 * Business layer, must be extended by all data object
 *
 * @author sanlion.do
 * @version v2.0 @2015-3-31
 */
public class ApiBody {

    /**
     * api status, it's successful when 0 by default
     */
    private int apiStatus = 0;
    /**
     * api message, match with apiStatus which maintained on server
     */
    private String apiMessage = "";

    public ApiBody() {
    }

    public ApiBody(int apiStatus, String apiMessage) {
        this.apiStatus = apiStatus;
        this.apiMessage = apiMessage;
    }
    
    public ApiBody(CodeEnum codeEnum) {
    	this.apiStatus = codeEnum.getCode();
    	this.apiMessage = codeEnum.getMessage();
    }

    public int getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(int apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getApiMessage() {
        return apiMessage;
    }

    public void setApiMessage(String apiMessage) {
        this.apiMessage = apiMessage;
    }

}
