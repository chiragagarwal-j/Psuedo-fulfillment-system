package com.pfsystem.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RechargeSimTest {
    @Test
    void getterSetters() {
        RechargeSim rechargeSim = new RechargeSim();

        rechargeSim.setMobileNumber("1234567890");
        assertEquals("1234567890", rechargeSim.getMobileNumber());

        rechargeSim.setOperator("OperatorName");
        assertEquals("OperatorName", rechargeSim.getOperator());

        rechargeSim.setOperatorCircle("OperatorCircleName");
        assertEquals("OperatorCircleName", rechargeSim.getOperatorCircle());

        rechargeSim.setPlanID(1L);
        assertEquals(1L, rechargeSim.getPlanID());

        OrderDetails orderDetails = new OrderDetails();
        rechargeSim.setOrderDetails(orderDetails);
        assertEquals(orderDetails, rechargeSim.getOrderDetails());

    }
}
