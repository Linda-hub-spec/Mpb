package com.etz.MPB.portal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "verification_periods")
public class VerificationPeriods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date createdOn;
    private Long createdBy;
    private Date updatedOn;
    private Long updatedBy;

}
