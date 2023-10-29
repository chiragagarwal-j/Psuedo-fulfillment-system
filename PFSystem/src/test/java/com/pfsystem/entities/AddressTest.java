package com.pfsystem.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

public class AddressTest {
    @Test
    public void testAddressFields() {
        Address address = new Address();
        address.setId(1L);
        address.setAddressLine1("123 Main St");
        address.setAddressLine2("Apt 4B");
        address.setCity("New York");
        address.setState("NY");
        address.setPincode("10001");

        assertEquals(1L, address.getId());
        assertEquals("123 Main St", address.getAddressLine1());
        assertEquals("Apt 4B", address.getAddressLine2());
        assertEquals("New York", address.getCity());
        assertEquals("NY", address.getState());
        assertEquals("10001", address.getPincode());

    }
}
