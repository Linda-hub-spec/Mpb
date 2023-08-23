package com.etz.MPB.portal.dto.request;

import lombok.Data;

@Data
public class CreatePensionRequest {
    private String serviceNo;
    private String rank;
    private String lastName;
    private String firstName;
    private String otherName;
    private String email;
    private String phone;
    private String bankCode;
    private String accountNo;
    private String bvn;
}
