package com.pfsystem.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class SimCardTest {
    @Test
    public void testGetterSetters() {
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
        assertEquals(true, simCard.isPlanStatus());

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
