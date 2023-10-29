package com.pfsystem.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class RechargeSimTest {
    @Test
    public void testGetterSetters() {
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
