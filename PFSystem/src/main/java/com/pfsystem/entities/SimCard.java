package com.pfsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "sim_card")
public class SimCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "imsi_id")
    private IMSI IMSI;

    @OneToOne
    @JoinColumn(name = "iccid_id")
    private ICCID ICCID;

    @OneToOne
    @JoinColumn(name = "msisdn_id")
    private MSISDN MSISDN;

    private boolean planStatus=false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}