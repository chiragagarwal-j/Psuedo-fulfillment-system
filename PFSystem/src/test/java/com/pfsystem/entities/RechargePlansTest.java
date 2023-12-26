package com.pfsystem.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RechargePlansTest {
    @Test
    void getterSetters() {
        RechargePlans rechargePlans = new RechargePlans();

        rechargePlans.setOperator("OperatorName");
        assertEquals("OperatorName", rechargePlans.getOperator());

        rechargePlans.setOperatorCircle("OperatorCircleName");
        assertEquals("OperatorCircleName", rechargePlans.getOperatorCircle());

        rechargePlans.setPrice("100");
        assertEquals("100", rechargePlans.getPrice());

        rechargePlans.setValidity("30 Days");
        assertEquals("30 Days", rechargePlans.getValidity());

        rechargePlans.setDetails("Plan details go here");
        assertEquals("Plan details go here", rechargePlans.getDetails());

        rechargePlans.setCategories("Category1, Category2");
        assertEquals("Category1, Category2", rechargePlans.getCategories());

    }
}
