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
    private String serviceNo;
    private String rankId;
    private String lastName;
    private String firstName;
    private String otherName;
    private String email;
    private String phone;
    private String bankCode;
    private String accountNo;
    private String bvn;

    @Column(name = "verification_stage")
    @Enumerated(EnumType.ORDINAL)
    private VerificationStage verificationStage;

    @Column(name = "verification_option")
    @Enumerated(EnumType.ORDINAL)
    private VerificationOption verificationOption;

    @Column(name = "verification_status")
    @Enumerated(EnumType.ORDINAL)
    private VerificationStatus verificationStatus;

    private String verificationReference;
    private boolean authorized;
    @Enumerated(EnumType.ORDINAL)
    private PensionerStatus status;
    private Date createdOn;
    private Long createdBy;
    private Date updatedOn;
    private Long updatedBy;

}
