package com.noharms.exercises.codewars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseFloatToMantissaAndExponentTest {

  private static void testBody(String input, int ndigits, String expected) {
    BigDecimal inputBD = new BigDecimal(input);
    String actual = ExerciseFloatToMantissaAndExponent.floatToMantissaAndExponent(inputBD, ndigits);
    System.out.println("Testing " + input.toString() + "with " + ndigits + " digits wanted");
    System.out.println("Actual  : " + actual);
    System.out.println("Expected: " + expected);
    System.out.println("--");
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void testFloatToMantissaAndExponent() {
    testBody("0.06", 10, "6000000000P-11");
    testBody("72.0", 12, "720000000000P-10");
    testBody("1.0", 5, "10000P-4");
    testBody("123456.0", 4, "1234P2");
    testBody("1234", 5, "12340P-1");
    testBody("1000000000", 10, "1000000000P0");
    return;
  }




}