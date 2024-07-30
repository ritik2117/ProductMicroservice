package com.scaler.productmicroservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomTest {
    /**
     * Test Case is nothing but a method that tests a particular functionality
     * across the expected value.
     * 3A ->
     * Arrange
     * Act
     * Assert
     *
     * In one test case we can have multiple assert statements, and the TC will pass
     * if all the assert statements will pass, even if one assert statement fails, TC fails.
     *
     * In some of the test cases we might need to check if the function is throwing
     * an Exception or not.
     *
     * Scenario :
     * If we want to test the time taken by a particular function to get executed.
     */

    @Test
    void tstIsOnePlusOneIsTwo() {
        int i = 1 + 1; // Arrange + Act
//        Assert -> Check against the expected value.
//        assert i == 2;
//        Need AssertJ Core library for implementing other functions
        assertEquals(2, i, "1 + 1 should be equal to 2");
    }
}
