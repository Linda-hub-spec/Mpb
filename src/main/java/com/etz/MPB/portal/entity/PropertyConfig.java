package com.etz.MPB.portal.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "property_config")
public class PropertyConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

}
