package com.etz.MPB.portal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "banks")
public class Banks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
}
