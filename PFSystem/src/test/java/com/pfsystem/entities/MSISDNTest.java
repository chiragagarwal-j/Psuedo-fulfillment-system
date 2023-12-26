package com.pfsystem.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MSISDNTest {
    @Test
    void gettersAndSetters() {

        MSISDN msisdn = new MSISDN();

        msisdn.setCc("91");
        assertEquals("91", msisdn.getCc());

        msisdn.setNsn("1234567890");
        assertEquals("1234567890", msisdn.getNsn());

        msisdn.setMsisdnID("911234567890");
        assertEquals("911234567890", msisdn.getMsisdnID());
    }

    @Test
    void generateIndianMobileNumber() {
        MSISDN msisdn = new MSISDN();
        String mobileNumber = msisdn.generateIndianMobileNumber();
        assertNotNull(mobileNumber);
    }

}
