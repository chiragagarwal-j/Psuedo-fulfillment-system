package com.pfsystem.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.Test;

public class MSISDNTest {
    @Test
    public void testGettersAndSetters() {

        MSISDN msisdn = new MSISDN();

        msisdn.setCc("91");
        assertEquals("91", msisdn.getCc());

        msisdn.setNsn("1234567890");
        assertEquals("1234567890", msisdn.getNsn());

        msisdn.setMsisdnID("911234567890");
        assertEquals("911234567890", msisdn.getMsisdnID());
    }

    @Test
    public void testGenerateIndianMobileNumber() {
        MSISDN msisdn = new MSISDN();
        String mobileNumber = msisdn.generateIndianMobileNumber();
        assertNotNull(mobileNumber);
    }

}
