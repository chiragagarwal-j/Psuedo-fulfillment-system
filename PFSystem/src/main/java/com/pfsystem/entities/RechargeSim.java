package com.pfsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class RechargeSim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String mobileNumber;

    private String operator;

    private String operatorCircle;

    @OneToOne
    @JoinColumn(name = "plan_id")
    private RechargePlans rechargePlans;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderDetails orderDetails;

}
