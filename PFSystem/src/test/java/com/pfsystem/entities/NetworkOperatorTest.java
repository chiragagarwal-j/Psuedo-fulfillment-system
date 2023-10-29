package com.pfsystem.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

public class NetworkOperatorTest {
    @Test
    public void testDefaultConstructor() {
        NetworkOperator networkOperator = new NetworkOperator();

        assertNotNull(networkOperator);
        assertEquals(null, networkOperator.getCountryName());
        assertEquals(null, networkOperator.getCountryCode());
    }

    @Test
    public void testParameterizedConstructor() {
        long id = 1L;
        String operator = "Operator";
        String brand = "Brand";

        NetworkOperator networkOperator = new NetworkOperator(id, operator, brand);

        assertNotNull(networkOperator);
        assertEquals(id, networkOperator.getId());
        assertEquals(operator, networkOperator.getOperator());
        assertEquals(brand, networkOperator.getBrand());
    }

    @Test
    public void testSetterGetterMethods() {
        NetworkOperator networkOperator = new NetworkOperator();

        networkOperator.setId(2L);
        networkOperator.setOperator("Operator");
        networkOperator.setBrand("Brand");

        assertEquals(2L, networkOperator.getId());
        assertEquals("Operator", networkOperator.getOperator());
        assertEquals("Brand", networkOperator.getBrand());
    }
}
