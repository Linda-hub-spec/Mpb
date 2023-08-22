package com.etz.MPB.portal.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BaseResponse<T> {
    private int responseCode;
    private String responseMessage;
    private long execTime;
    private String error;
    private T data = null;
}
