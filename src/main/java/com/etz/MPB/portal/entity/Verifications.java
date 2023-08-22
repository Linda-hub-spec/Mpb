package com.etz.MPB.portal.entity;

import com.etz.MPB.portal.enums.VerificationType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "verifications")
public class Verifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pensionerId;
    @Enumerated
    private VerificationType type;
    private Integer degree;
    private String firstData;
    private String secondData;
    private Date createdOn;
    private Date updatedOn;
}
