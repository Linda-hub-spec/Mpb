package com.etz.MPB.portal.dto.request;

import com.etz.MPB.portal.enums.PensionerStatus;
import com.etz.MPB.portal.enums.VerificationStatus;
import lombok.Data;

@Data
public class UpdatePensionerRequest extends CreatePensionRequest{
    private VerificationStatus verificationStatus;
    private PensionerStatus pensionerStatus;
}
