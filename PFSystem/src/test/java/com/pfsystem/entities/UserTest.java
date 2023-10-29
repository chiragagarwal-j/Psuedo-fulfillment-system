package com.pfsystem.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class UserTest {
    @Test
    public void testGetterSetters() {
        User user = new User();

        user.setId(1L);
        assertEquals(1L, user.getId());

        user.setFirstName("John");
        assertEquals("John", user.getFirstName());

        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());

        user.setEmail("johndoe@example.com");
        assertEquals("johndoe@example.com", user.getEmail());
    }
}