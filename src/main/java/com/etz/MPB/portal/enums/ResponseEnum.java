package com.etz.MPB.portal.enums;

//import org.omg.CORBA.INVALID_TRANSACTION;
public enum ResponseEnum {
    SUCCESSFUL("200", "Successful");


    private final String responseCode;
    private String responseMessage;

    ResponseEnum(String code, String message) {
        this.responseMessage = message;
        this.responseCode = code;
    }

    ResponseEnum(String code) {
        this.responseCode = code;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
