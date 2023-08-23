package com.etz.MPB.portal.dto.response;

import com.etz.MPB.portal.enums.VerificationOption;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PensionerVerify {
    private String firstName;
    private String lastName;
    private String otherName;
    private String email;
    private String phone;
    private String bvn;
    private String verificationStatus;
    private String verificationOption;
    private List<VerificationDetails> verificationDetails;
    private Date createdOn;
    private Long createdBy;
    private Date updatedOn;
    private Long updatedBy;
}
