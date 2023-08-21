package com.etz.MPB.portal.entity;

import com.etz.MPB.portal.enums.ConstantStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@Table(name = "permissions")
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean authorized;
    @Enumerated
    private ConstantStatus status;
    private Date createdOn;
    private Long createdBy;
    private Date updatedOn;
    private Long updatedBy;
}
