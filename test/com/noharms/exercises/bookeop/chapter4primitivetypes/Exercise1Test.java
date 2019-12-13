package com.noharms.exercises.bookeop.chapter4primitivetypes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Exercise1Test {

  @Test
  void reverseDigitsTest1() {
    int input = 12345;
    int expectedOutput = 54321;
    int actualOutput = Exercise1.reverseDigits(input);
    Assertions.assertEquals(expectedOutput, actualOutput);
  }


  @Test
  void reverseDigitsTest2() {
    int input = 99999999;
    int expectedOutput = 99999999;
    int actualOutput = Exercise1.reverseDigits(input);
    Assertions.assertEquals(expectedOutput, actualOutput);
  }

}