package com.etz.MPB.portal.dto.response;

import com.etz.MPB.portal.enums.VerificationStatus;
import lombok.Data;

import java.util.Date;
@Data
public class ReferenceVerificationResponse {
    private Long id;
    private String reference;
    private String qrCode;
    private PensionerVerify pensioner;


}
