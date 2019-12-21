package com.noharms.exercises.codewars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseCountIPadressesTest {

    @Test
    void test1CountIPAdresses() {
        // input
        String ip1 = "0.0.0.0";
        String ip2 = "0.0.0.1";

        // expected
        long expected = 1;

        // actual
        long actual = ExerciseCountIPadresses.countIPAdresses(ip1, ip2);

        // test
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test2CountIPAdresses() {
        // input
        String ip1 = "0.0.0.0";
        String ip2 = "255.255.255.255";

        // expected
        long expected = 2L * Integer.MAX_VALUE + 1;

        // actual
        long actual = ExerciseCountIPadresses.countIPAdresses(ip1, ip2);

        // test
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test3CountIPAdresses() {
        // input
        String ip1 = "0.255.255.255";
        String ip2 = "1.0.0.0";

        // expected
        long expected = 1;

        // actual
        long actual = ExerciseCountIPadresses.countIPAdresses(ip1, ip2);

        // test
        Assertions.assertEquals(expected, actual);
    }

}