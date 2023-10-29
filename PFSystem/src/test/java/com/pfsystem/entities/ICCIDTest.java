package com.pfsystem.entities;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ICCIDTest {

	@Test
	public void testICCIDFields() {
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
	public void generateRandomIAN() {
		ICCID i = new ICCID();
		String actual = i.generateRandomIAN();
		assertNotNull(actual);
	}
}
