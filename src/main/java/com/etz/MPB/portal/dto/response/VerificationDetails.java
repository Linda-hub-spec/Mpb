package com.etz.MPB.portal.dto.response;

import com.etz.MPB.portal.enums.VerificationType;
import lombok.Data;

import java.util.Date;

@Data
public class VerificationDetails {
    private String verificationType;
    private Boolean match;
    private Integer degree;
    private String firstData;
    private String secondData;
    private Date createdOn;
}
