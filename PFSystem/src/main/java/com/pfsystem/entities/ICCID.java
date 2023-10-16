package com.pfsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ICCID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(columnDefinition = "VARCHAR(2) DEFAULT '89'")
    private String MII;

    @Column(columnDefinition = "VARCHAR(2) DEFAULT '91'")
    private String CC;

    private String MNC;

    private String N;

    private String C;

    private String x;
}
