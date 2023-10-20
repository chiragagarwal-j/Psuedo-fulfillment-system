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
    private IMSI imsi;

    @OneToOne
    @JoinColumn(name = "iccid_id")
    private ICCID iccid;

    @OneToOne
    @JoinColumn(name = "msisdn_id")
    private MSISDN msisdn;

    private String type;

    private String aadhaarCard;

    private boolean planStatus=false;

    private String existingNumber;

    @OneToOne
    @JoinColumn(name="order_id")
    private OrderDetails orderDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}