package com.etz.MPB.portal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "valid_references")
public class ValidReferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String verificationReference;
    private Long pensionerId;
    private Date createdOn;
    private Long verificationPeriodId;
}
