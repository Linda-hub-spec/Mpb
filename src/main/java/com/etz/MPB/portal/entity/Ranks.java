package com.etz.MPB.portal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ranks")
public class Ranks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
}
