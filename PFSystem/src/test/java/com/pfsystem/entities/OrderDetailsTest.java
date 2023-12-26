package com.pfsystem.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderDetailsTest {

    @Test
    void defaultValues() {
		OrderDetails orderDetails = new OrderDetails();

		assertNull(orderDetails.getOrderID());
		assertNull(orderDetails.getPrice());
		assertNull(orderDetails.getPaidVia());
		assertNull(orderDetails.getPaymentInfo());
		assertFalse(orderDetails.getIsPending());
		assertEquals("Success", orderDetails.getStatus());
	}

    @Test
    void setterGetterMethods() {
		OrderDetails orderDetails = new OrderDetails();

		orderDetails.setPaidVia("Credit Card");
		assertEquals("Credit Card", orderDetails.getPaidVia());

		orderDetails.setPaymentInfo("Payment Information");
		assertEquals("Payment Information", orderDetails.getPaymentInfo());

		orderDetails.setIsPending(true);
        assertTrue(orderDetails.getIsPending());

		orderDetails.setStatus("Success");
		assertEquals("Success", orderDetails.getStatus());

		orderDetails.setOrderID("12345");
		orderDetails.setPrice("100.00");
		orderDetails.setPaidVia("Credit Card");

		assertEquals("12345", orderDetails.getOrderID());
		assertEquals("100.00", orderDetails.getPrice());
		assertEquals("Credit Card", orderDetails.getPaidVia());
	}

    @Test
    void generateRandomOrderId() {
		OrderDetails o = new OrderDetails();
		String actual = o.generateRandomOrderId();
		assertNotNull(actual);
	}
}
