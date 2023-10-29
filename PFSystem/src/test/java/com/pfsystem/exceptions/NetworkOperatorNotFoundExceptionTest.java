package com.pfsystem.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NetworkOperatorNotFoundExceptionTest {
	@Test
	public void testNetworkOperatorNotFoundExceptionWithMessage() {
		String message = "Test exception message";
		NetworkOperatorNotFoundException expected = new NetworkOperatorNotFoundException(message);
		NetworkOperatorNotFoundException actual = new NetworkOperatorNotFoundException(message);

		assertEquals(expected.getMessage(), actual.getMessage());
	}
}
