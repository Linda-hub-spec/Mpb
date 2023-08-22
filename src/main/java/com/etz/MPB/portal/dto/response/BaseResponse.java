package com.etz.MPB.portal.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BaseResponse<T> {
    private String responseCode;
    private String responseMessage;
    private Long timestamp;
    private long execTime;
    private T data = null;
}
