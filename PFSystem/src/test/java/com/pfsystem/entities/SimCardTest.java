package com.pfsystem.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimCardTest {
    @Test
    void getterSetters() {
        SimCard simCard = new SimCard();

        IMSI imsi = new IMSI();
        simCard.setImsi(imsi);
        assertEquals(imsi, simCard.getImsi());

        ICCID iccid = new ICCID();
        simCard.setIccid(iccid);
        assertEquals(iccid, simCard.getIccid());

        MSISDN msisdn = new MSISDN();
        simCard.setMsisdn(msisdn);
        assertEquals(msisdn, simCard.getMsisdn());

        simCard.setType("TypeValue");
        assertEquals("TypeValue", simCard.getType());

        simCard.setAadhaarCard("AadhaarCardValue");
        assertEquals("AadhaarCardValue", simCard.getAadhaarCard());

        simCard.setPlanStatus(true);
        assertTrue(simCard.isPlanStatus());

        simCard.setExistingNumber("ExistingNumberValue");
        assertEquals("ExistingNumberValue", simCard.getExistingNumber());

        Address address = new Address();
        simCard.setAddresses(address);
        assertEquals(address, simCard.getAddresses());

        OrderDetails orderDetails = new OrderDetails();
        simCard.setOrderDetails(orderDetails);
        assertEquals(orderDetails, simCard.getOrderDetails());

        User user = new User();
        simCard.setUser(user);
        assertEquals(user, simCard.getUser());
    }
}
