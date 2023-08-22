package com.etz.MPB.portal.exceptions;

import lombok.Data;

@Data
public class InvalidLoginResponse {
    private String clientId;
    private String clientPin;

    public InvalidLoginResponse() {
        this.clientId= "Invalid clientId";
        this.clientPin = "Invalid Pin";
    }


}
