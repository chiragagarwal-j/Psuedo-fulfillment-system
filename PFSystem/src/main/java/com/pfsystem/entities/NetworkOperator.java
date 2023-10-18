package com.pfsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor

public class NetworkOperator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String type;

    @Column(name = "countryName")
    private String countryName;

    @Column(name = "countryCode")
    private String countryCode;

    private String mcc;

    private String mnc;
    private String brand;
    private String operator;
    private String status;
    private String bands;
    private String notes;

    public NetworkOperator() {
    }

    public NetworkOperator(long id, String operator, String brand) {
        this.id = id;
        this.operator = operator;
        this.brand = brand;
    }
}
