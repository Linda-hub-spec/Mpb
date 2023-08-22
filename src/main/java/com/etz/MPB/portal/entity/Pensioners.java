package com.etz.MPB.portal.entity;

import com.etz.MPB.portal.enums.PensionerStatus;
import com.etz.MPB.portal.enums.VerificationOption;
import com.etz.MPB.portal.enums.VerificationStage;
import com.etz.MPB.portal.enums.VerificationStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "pensioners")
public class Pensioners {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String service_no;
    private String rank;
    private String lastName;
    private String firstName;
    private String otherName;
    private String email;
    private String phone;
    private String bankCode;
    private String accountNo;
    private String bvn;
    @Enumerated
    private VerificationStage verificationStage;
    @Enumerated
    private VerificationOption verificationOption;
    @Enumerated
    private VerificationStatus verificationStatus;
    private String verificationReference;
    private boolean authorized;
    @Enumerated
    private PensionerStatus status;
    private Date createdOn;
    private Long createdBy;
    private Date updatedOn;
    private Long updatedBy;

}
