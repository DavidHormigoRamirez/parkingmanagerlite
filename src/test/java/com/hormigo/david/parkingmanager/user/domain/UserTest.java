package com.hormigo.david.parkingmanager.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;
    @BeforeEach
    void prepareUser(){
        user = new User("david.hormigo@correo.es","David","Hormigo","Ramirez",Role.PROFESSOR);
    }
    @Test
    void testGetEmail() {
        String expected = "david.hormigo@correo.es";
        String actual = user.getEmail();
        assertEquals(expected,actual);
    }

    @Test
    void testGetLastName1() {
        String expected = "Hormigo";
        String actual = user.getLastName1();
        assertEquals(expected,actual);
    }

    @Test
    void testGetLastName2() {
        String expected = "Ramirez";
        String actual = user.getLastName2();
        assertEquals(expected,actual);
    }

    @Test
    void testGetName() {
        String expected = "David";
        String actual = user.getName();
        assertEquals(expected,actual);
    }

    @Test
    void testGetRole() {

        Role expected = Role.PROFESSOR;
        Role actual = user.getRole();
        assertEquals(expected,actual);
    }

    @Test
    void testSetEmail() {
        user.setEmail("otro@correo");
        String expected = "otro@correo";
        String actual = user.getEmail();
        assertEquals(expected,actual);
    }

    @Test
    void testSetLastName1() {

        user.setLastName1("Manuel");
        String expected = "Manuel";
        String actual = user.getLastName1();
        assertEquals(expected,actual);
    }

    @Test
    void testSetLastName2() {
        user.setLastName2("Manuel");
        String expected = "Manuel";
        String actual = user.getLastName2();
        assertEquals(expected,actual);
    }

    @Test
    void testSetName() {
        user.setName("Manuel");
        String expected = "Manuel";
        String actual = user.getName();
        assertEquals(expected,actual);
    }

    @Test
    void testSetRole() {
        user.setRole(Role.STUDENT);
        Role expected = Role.STUDENT;
        Role actual = user.getRole();
        assertEquals(expected,actual);
    }
}
