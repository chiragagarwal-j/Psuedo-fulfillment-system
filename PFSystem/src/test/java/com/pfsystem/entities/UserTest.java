package com.pfsystem.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    @Test
    void getterSetters() {
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
