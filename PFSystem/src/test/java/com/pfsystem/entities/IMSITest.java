package com.pfsystem.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IMSITest {
    @Test
    void addressFields() {

		IMSI imsi = new IMSI();

		imsi.setMcc("123");
		assertEquals("123", imsi.getMcc());

		imsi.setMnc("456");
		assertEquals("456", imsi.getMnc());

		imsi.setMsin("7890123456");
		assertEquals("7890123456", imsi.getMsin());

		imsi.setImsiID("1234567890123456");
		assertEquals("1234567890123456", imsi.getImsiID());
	}

    @Test
    void generateRandomMSINTODO() {
		IMSI i = new IMSI();
		String actual = i.generateRandomMSIN();
		assertNotNull(actual);
	}
}
