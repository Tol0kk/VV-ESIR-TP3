package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    // =============== //
    // isLeapYear()    //
    // =============== //

    @Test
    void testIsLeapYearValids() {
        assertTrue(Date.isLeapYear(2020));
        assertTrue(Date.isLeapYear(2000));
        assertTrue(Date.isLeapYear(-4));
    }

    @Test
    void testIsLeapYearInValids() {
        assertFalse(Date.isLeapYear(2021));
        assertFalse(Date.isLeapYear(1900));
    }

    // =============== //
    // isValidDate()   //
    // =============== //

    @Test
    void testIsValidDateValids() {
        assertTrue(Date.isValidDate(31, 1, 2023));
        assertTrue(Date.isValidDate(29, 2, 2020));
        assertTrue(Date.isValidDate(1, 1, 0));
    }

    @Test
    void testIsValidDateInValids() {
        assertFalse(Date.isValidDate(32, 1, 10)); // Out of Bound
        assertFalse(Date.isValidDate(31, 4, 2023)); // April has only 30 day
        assertFalse(Date.isValidDate(29, 2, 2021)); // Invalid for non-leap year
        assertFalse(Date.isValidDate(0, 1, 2023)); // No 0 day
        assertFalse(Date.isValidDate(1, 0, 2023)); // No 0 month 
    }


    // =============== //
    // Date()          //
    // =============== //

    @Test
    void testDateThrow() {
        assertThrows(IllegalArgumentException.class,() -> new Date(0,0,0));
    }

    // =============== //
    // getters         //
    // =============== //

    @Test
    void testGetters() {
        Date d1 = new Date(31, 1, 2023);
        assertTrue(d1.getDay() == 31);
        assertTrue(d1.getMonth() == 1);
        assertTrue(d1.getYear() == 2023);
    }


    // =============== //
    // compareTo()     //
    // =============== //

    @Test
    void testCompareToEquals() {
        Date d1 = new Date(31, 1, 2023);
        Date d2 = new Date(31, 1, 2023);
        assertTrue(d1.compareTo(d2) == 0);
    }

    @Test
    void testCompareToNotEquals() {
        Date d1 = new Date(30, 1, 2023);
        Date d2 = new Date(31, 1, 2023);
        assertTrue(d1.compareTo(d2) != 0);
    }

    @Test
    void testCompareToAnterior() {
        Date d1 = new Date(31, 1, 2023);
        Date d2 = new Date(1, 2, 2023);
        assertTrue(d1.compareTo(d2) < 0);
    }

    @Test
    void testCompareToPosterior() {
        Date d1 = new Date(31, 1, 2024);
        Date d2 = new Date(30, 1, 2023);
        assertTrue(d1.compareTo(d2) > 0);
    }

    @Test
    void testCompareToTrow() {
        Date d1 = new Date(31, 1, 2023);
        Date d2 = null;
        assertThrows(NullPointerException.class,() -> d1.compareTo(d2));
    }

    // =============== //
    // NextDate()      //
    // =============== //

    @Test 
    void testNextDateTransitionMonth() {
        Date d1 = new Date(31, 1, 2023);
        Date d2 = new Date(1, 2, 2023);
        assertEquals(d1.nextDate().compareTo(d2), 0);
    }

    @Test 
    void testNextDateTransitionYear() {
        Date d1 = new Date(31, 12, 2023);
        Date d2 = new Date(1, 1, 2024);
        assertEquals(d1.nextDate().compareTo(d2), 0);
    }

    @Test 
    void testNextDateTransition() {
        Date d1 = new Date(30, 11, 2023);
        Date d2 = new Date(1, 12, 2023);
        assertEquals(d1.nextDate().compareTo(d2), 0);
    }

    @Test 
    void testNextDateTransitionFebLeap() {
        Date d1 = new Date(28, 2, 2020);
        Date d2 = new Date(29, 2, 2020);
        assertEquals(d1.nextDate().compareTo(d2), 0);
    }

    @Test 
    void testNextDateTransitionFebNonLeap() {
        Date d1 = new Date(28, 2, 2021);
        Date d2 = new Date(1, 3, 2021);
        assertEquals(d1.nextDate().compareTo(d2), 0);
    }

    @Test 
    void testNextDateTransitionBCtoDC() {
        Date d1 = new Date(31, 12, -1);
        Date d2 = new Date(1, 1, 0);
        assertEquals(d1.nextDate().compareTo(d2), 0);
    }

    // =============== //
    // PreviousDate()  //
    // =============== //

    @Test 
    void testPreviousDateTransitionMonth() {
        Date d1 = new Date(1, 2, 2023);
        Date d2 = new Date(31, 1, 2023);
        assertEquals(d1.previousDate().compareTo(d2), 0);
    }

    @Test 
    void testPreviousDateTransition() {
        Date d1 = new Date(2, 1, 2023);
        Date d2 = new Date(1, 1, 2023);
        assertEquals(d1.previousDate().compareTo(d2), 0);
    }

    @Test 
    void testPreviousDateTransitionYear() {
        Date d1 = new Date(1, 1, 2024);
        Date d2 = new Date(31, 12, 2023);
        assertEquals(d1.previousDate().compareTo(d2), 0);
    }

    @Test 
    void testPreviousDateTransitionFebLeap() {
        Date d1 = new Date(29, 2, 2020);
        Date d2 = new Date(28, 2, 2020);
        assertEquals(d1.previousDate().compareTo(d2), 0);
    }

    @Test 
    void testPreviousDateTransitionFebNonLeap() {
        Date d1 = new Date(1, 3, 2021);
        Date d2 = new Date(28, 2, 2021);
        assertEquals(d1.previousDate().compareTo(d2), 0);
    }

    @Test 
    void testPreviousDateTransitionBCtoDC() {
        Date d1 = new Date(1, 1, 0);
        Date d2 = new Date(31, 12, -1);
        assertEquals(d1.previousDate().compareTo(d2), 0);
    }
}