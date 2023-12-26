package com.pfsystem.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ICCIDTest {

    @Test
    void iCCIDFields() {
		ICCID iccid = new ICCID();
		iccid.setId(1L);
		iccid.setMii("89");
		iccid.setCc("91");
		iccid.setMnc("123");
		iccid.setIccidID("8991123456789012");

		assertEquals(1L, iccid.getId());
		assertEquals("89", iccid.getMii());
		assertEquals("91", iccid.getCc());
		assertEquals("123", iccid.getMnc());
		assertEquals("8991123456789012", iccid.getIccidID());
	}

    @Test
    void generateRandomIAN() {
		ICCID i = new ICCID();
		String actual = i.generateRandomIAN();
		assertNotNull(actual);
	}
}
