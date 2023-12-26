package com.pfsystem.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkOperatorTest {
    @Test
    void defaultConstructor() {
        NetworkOperator networkOperator = new NetworkOperator();

        assertNotNull(networkOperator);
        assertNull(networkOperator.getCountryName());
        assertNull(networkOperator.getCountryCode());
    }

    @Test
    void parameterizedConstructor() {
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
    void setterGetterMethods() {
        NetworkOperator networkOperator = new NetworkOperator();

        networkOperator.setId(2L);
        networkOperator.setOperator("Operator");
        networkOperator.setBrand("Brand");

        assertEquals(2L, networkOperator.getId());
        assertEquals("Operator", networkOperator.getOperator());
        assertEquals("Brand", networkOperator.getBrand());
    }
}
