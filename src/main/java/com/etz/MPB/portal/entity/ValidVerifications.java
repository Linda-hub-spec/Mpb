package com.etz.MPB.portal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "valid_verifications")
public class ValidVerifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long verificationId;
    private boolean match;
    private Date createdOn;
}
